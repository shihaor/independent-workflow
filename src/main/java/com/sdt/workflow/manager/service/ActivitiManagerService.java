package com.sdt.workflow.manager.service;

import com.sdt.common.bean.PagerBean;
import com.sdt.common.vo.CommonVO;
import com.sdt.workflow.manager.vo.ProcessDefineVO;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工作流管理服务服务接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
public interface ActivitiManagerService {

    /**
     * 获取所有已部署的资源
     *
     * @return 已部署的资源
     * @param pagerBean 分页参数
     */
    List<ProcessDefineVO> findAllDeployments(PagerBean pagerBean);

    /**
     * 列出所有未被挂起的流程
     *
     * @return 未被挂起的流程资源
     * @param pagerBean 分页参数
     */
    List<ProcessDefineVO> obtainAllBpmnList(PagerBean pagerBean);

    /**
     * 获取流转到登录者身上的任务
     *
     * @param request 上下文
     * @param pagerBean 分页参数
     * @return 任务集合
     */
    List<Task> obtainMyTaskList(HttpServletRequest request, PagerBean pagerBean);

    /**
     * 列出我申请过的流程
     *
     * @param pagerBean 分页参数
     * @@param request 上下文
     * @return 流程实例集合
     */
    List<HistoricProcessInstance> obtainMyApplyList(HttpServletRequest request, PagerBean pagerBean);

    /**
     * 查询办理过的任务
     *
     * @param request 上下文
     * @param pagerBean 分页参数
     * @return 办理过的任务
     */
    List<HistoricTaskInstance> obtainMyTaskOverList(HttpServletRequest request, PagerBean pagerBean);

    /**
     * 获取流程所有的任务节点
     *
     * @param processDefineId 流程定义id
     * @return 节点合集
     */
    List<CommonVO> obtainAllTaskName(String processDefineId);
}
