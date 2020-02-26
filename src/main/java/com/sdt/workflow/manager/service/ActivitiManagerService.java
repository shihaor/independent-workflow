package com.sdt.workflow.manager.service;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

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
     */
    List<Deployment> findAllDeployments();

    /**
     * 列出所有未被挂起的流程
     *
     * @return 未被挂起的流程资源
     */
    List<ProcessDefinition> listAllBpmnList();

    /**
     * 获取流转到登录者身上的任务
     *
     * @return
     */
    List<Task> listMyTaskList();

    /**
     * 列出我申请过的流程
     *
     * @return 流程实例集合
     */
    List<HistoricProcessInstance> listMyApply();

    /**
     * 列出我申请过的未完成的流程
     *
     * @return 流程实例集合
     */
    List<ProcessInstance> listMyApplyUnOver();

    /**
     * 查询办理过的任务
     *
     * @return 办理过的任务
     */
    List<HistoricTaskInstance> listMyTaskOverList();
}
