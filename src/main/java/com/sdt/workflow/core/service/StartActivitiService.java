package com.sdt.workflow.core.service;

/**
 * 工作流启动接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
public interface StartActivitiService {

    /**
     * 无表单启动
     *
     * @param deploymentId 部署id
     */
    void noForm(String deploymentId);
}
