package com.sdt.workflow.manager.service;

/**
 * 部署资源操作服务接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
public interface DeploymentOperatorService {

    /**
     * 将已部署的资源挂起
     *
     * @param id 资源id
     */
    void suspendDeployment(String id);

    /**
     * 将已挂起的资源激活
     *
     * @param id 资源id
     */
    void activeDeployment(String id);

    /**
     * 将资源删除，如果存在运行的流程则把正在运行的流程提示给前端
     *
     * @param id 资源id
     */
    void deleteDeployment(String id);

}
