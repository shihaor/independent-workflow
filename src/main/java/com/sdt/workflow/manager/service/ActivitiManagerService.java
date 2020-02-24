package com.sdt.workflow.manager.service;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
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
    List<ProcessDefinition> listAllBpmn();

    /**
     * 获取流转到登录者身上的任务
     *
     * @return
     */
    List<Task> listMyTask();
}
