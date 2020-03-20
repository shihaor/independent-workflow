package com.sdt.workflow.core.service;

/**
 * 删除流程实例
 *
 * @author shihaoran
 * @date 2020/2/26
 */
public interface DeleteActivitiService {

    /**
     * 删除正在执行的流程
     *
     * @param processInstanceId 流程实例id
     */
    void deleteNoReason(String processInstanceId);

    /**
     * 删除正在执行的流程
     *
     * @param processInstanceId 流程实例id
     * @param reason 删除原因
     */
    void deleteHasReason(String processInstanceId, String reason);
}
