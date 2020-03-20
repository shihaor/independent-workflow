package com.sdt.workflow.manager.service.impl;

import com.sdt.common.bean.PagerBean;
import com.sdt.common.constant.DatePattern;
import com.sdt.common.utils.CommonUtils;
import com.sdt.common.vo.CommonVO;
import com.sdt.workflow.manager.service.ActivitiManagerService;
import com.sdt.workflow.manager.vo.ProcessDefineVO;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 工作流管理服务实现类
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Service
@Scope("prototype")
public class ActivitiManagerServiceImpl implements ActivitiManagerService {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Value("${session.userId}")
    private String userId;

    @Override
    public List<ProcessDefineVO> findAllDeployments(PagerBean pagerBean) {
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().active().listPage(pagerBean.getStartPage(), pagerBean.getLimit());
        return getProcessDefineVOS(processDefinitionList);
    }

    @Override
    public List<ProcessDefineVO> obtainAllBpmnList(PagerBean pagerBean) {
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery().listPage(pagerBean.getStartPage(), pagerBean.getLimit());
        return getProcessDefineVOS(processDefinitionList);
    }

    @Override
    public List<Task> obtainMyTaskList(HttpServletRequest request, PagerBean pagerBean) {

        String users = request.getSession().getAttribute(userId).toString();
        return taskService.createTaskQuery().taskCandidateOrAssigned(users).listPage(pagerBean.getStartPage(), pagerBean.getLimit());
    }

    @Override
    public List<HistoricProcessInstance> obtainMyApplyList(HttpServletRequest request, PagerBean pagerBean) {

         String users = request.getSession().getAttribute(userId).toString();
        return historyService.createHistoricProcessInstanceQuery().orderByProcessInstanceStartTime().desc().startedBy(users).listPage(pagerBean.getStartPage(), pagerBean.getLimit());

    }

    @Override
    public List<HistoricTaskInstance> obtainMyTaskOverList(HttpServletRequest request, PagerBean pagerBean) {
         String users = request.getSession().getAttribute(userId).toString();
        return historyService.createHistoricTaskInstanceQuery().taskCandidateUser(users).orderByHistoricTaskInstanceEndTime().desc().finished().listPage(pagerBean.getStartPage(), pagerBean.getLimit());
    }

    @Override
    public List<CommonVO> obtainAllTaskName(String processDefineId) {

        // 结果返回
        List<CommonVO> taskList = new ArrayList<>();
        // 获取流程定义
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefineId);
        if (null != bpmnModel) {
            Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
            flowElements.forEach(flowElement -> {
                CommonVO task = new CommonVO();
                if (flowElement instanceof UserTask) {
                    task.setLabel(flowElement.getName());
                    task.setValue(flowElement.getId());
                    taskList.add(task);
                }
            });
        }
        return taskList;
    }

    /**
     * 填充流程定义的VO
     *
     * @param processDefinitionList 流程定义的集合
     * @return 流程定义的集合
     */
    private List<ProcessDefineVO> getProcessDefineVOS(List<ProcessDefinition> processDefinitionList) {
        List<ProcessDefineVO> resultList = new ArrayList<>();
        processDefinitionList.forEach(processDefinition -> {
            ProcessDefineVO processDefineVO = new ProcessDefineVO();
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            processDefineVO.setLastTime(CommonUtils.dateToString(deployment.getDeploymentTime(), DatePattern.DATE_ALL));
            processDefineVO.setId(processDefinition.getId());
            processDefineVO.setName(processDefinition.getName());
            processDefineVO.setDescription(processDefinition.getDescription());
            processDefineVO.setVersion(processDefinition.getVersion());
            processDefineVO.setStatus(processDefinition.isSuspended());
            resultList.add(processDefineVO);
        });
        return resultList;
    }
}
