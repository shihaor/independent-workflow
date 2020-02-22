package com.sdt.workflow.manager.service;

import org.activiti.engine.repository.Deployment;

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
}
