package com.sdt.workflow.core.service.impl;

import com.sdt.common.constant.WorkflowConstants;
import com.sdt.workflow.core.diybpmnimage.impl.DiyProcessDiagramGeneratorImpl;
import com.sdt.workflow.core.service.ActivitiDetailService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.Execution;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 工作流获取详细的实现类
 *
 * @author shihaoran
 * @date 2020/3/4
 */
@Service
@Scope("prototype")
public class ActivitiDetailServiceImpl implements ActivitiDetailService {

    @Resource
    private HistoryService historyService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Override
    public InputStream obtainProcessNowNodePng(String processInstanceId) {

        if (StringUtils.isBlank(processInstanceId)) {
            throw new ActivitiException("流程实例参数不能为空，这样取不到图片");
        }

        // 根据processInstanceId获取历史流程实例
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();

        // 获取流程定义
        BpmnModel bpmnModel = repositoryService.getBpmnModel(historicProcessInstance.getProcessDefinitionId());

        // 根据活跃时间排序
        List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list();

        // 高亮环节id集合
        List<String> highLightedActivitiList = new ArrayList<>();

        // 高亮线路id集合
        List<String> highLightedFlows = obtainHighLightedFlows(bpmnModel, highLightedActivitList);
        for (HistoricActivityInstance tempActivity : highLightedActivitList) {
            String activityId = tempActivity.getActivityId();
            highLightedActivitiList.add(activityId);
        }

        Set<String> currIds = runtimeService.createExecutionQuery().processInstanceId(historicProcessInstance.getId()).list()
                .stream().map(Execution::getActivityId).collect(Collectors.toSet());

        DiyProcessDiagramGeneratorImpl diagramGenerator = new DiyProcessDiagramGeneratorImpl();
        return diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitiList,
                highLightedFlows, "宋体", "宋体", "宋体",
                null, 1.0, new Color[]{WorkflowConstants.COLOR_NORMAL, WorkflowConstants.COLOR_CURRENT}, currIds);
    }

    /**
     * 获取已经流转的线
     *
     * @param bpmnModel                    流程模型
     * @param historicActivityInstanceList 历史活跃流程实例
     * @return 已经流转的线的集合
     */
    private static List<String> obtainHighLightedFlows(BpmnModel bpmnModel, List<HistoricActivityInstance> historicActivityInstanceList) {
        // 高亮流程已发生流转的线id集合
        List<String> highLightedFlowIdList = new ArrayList<>();
        // 全部活动节点
        List<FlowNode> historicActivityNodeList = new ArrayList<>();
        // 已完成的历史活动节点
        List<HistoricActivityInstance> finishedActivityInstanceList = new ArrayList<>();

        for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
            FlowNode flowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(historicActivityInstance.getActivityId(), true);
            historicActivityNodeList.add(flowNode);
            if (null != historicActivityInstance.getEndTime()) {
                finishedActivityInstanceList.add(historicActivityInstance);
            }
        }

        FlowNode currentFlowNode;
        FlowNode targetFlowNode;
        // 遍历已完成的活动实例，从每个实例的outgoingFlows中找到已执行的
        for (HistoricActivityInstance currentActivityInstance : finishedActivityInstanceList) {
            // 获得当前活动对应的节点信息及outgoingFlows信息
            currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(currentActivityInstance.getActivityId(), true);
            List<SequenceFlow> sequenceFlowList = currentFlowNode.getOutgoingFlows();

            /*
             * 遍历outgoingFlows并找到已已流转的 满足如下条件认为已已流转：
             * 1.当前节点是并行网关或兼容网关，则通过outgoingFlows能够在历史活动中找到的全部节点均为已流转
             * 2.当前节点是以上两种类型之外的，通过outgoingFlows查找到的时间最早的流转节点视为有效流转
             */
            if ("parallelGateway".equals(currentActivityInstance.getActivityType()) || "inclusiveGateway".equals(currentActivityInstance.getActivityType())) {
                // 遍历历史活动节点，找到匹配流程目标节点的
                for (SequenceFlow sequenceFlow : sequenceFlowList) {
                    targetFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(sequenceFlow.getTargetRef(), true);
                    if (historicActivityNodeList.contains(targetFlowNode)) {
                        highLightedFlowIdList.add(targetFlowNode.getId());
                    }
                }
            } else {
                List<Map<String, Object>> tempMapList = new ArrayList<>();
                for (SequenceFlow sequenceFlow : sequenceFlowList) {
                    for (HistoricActivityInstance historicActivityInstance : historicActivityInstanceList) {
                        if (historicActivityInstance.getActivityId().equals(sequenceFlow.getTargetRef())) {
                            Map<String, Object> map = new HashMap<>(16);
                            map.put("highLightedFlowId", sequenceFlow.getId());
                            map.put("highLightedFlowStartTime", historicActivityInstance.getStartTime().getTime());
                            tempMapList.add(map);
                        }
                    }
                }

                if (!CollectionUtils.isEmpty(tempMapList)) {
                    // 遍历匹配的集合，取得开始时间最早的一个
                    long earliestStamp = 0L;
                    String highLightedFlowId = null;
                    for (Map<String, Object> map : tempMapList) {
                        long highLightedFlowStartTime = Long.parseLong(map.get("highLightedFlowStartTime").toString());
                        if (0 == earliestStamp || earliestStamp >= highLightedFlowStartTime) {
                            highLightedFlowId = map.get("highLightedFlowId").toString();
                            earliestStamp = highLightedFlowStartTime;
                        }
                    }
                    highLightedFlowIdList.add(highLightedFlowId);
                }
            }
        }
        return highLightedFlowIdList;
    }
}
