package com.sdt.workflow.core.service;

/**
 * 节点完成服务接口
 *
 * @author shihaoran
 * @date 2020/2/24
 */
public interface CompleteActivitiService {

    /**
     * 根据节点ID,将内置表单内容填入工作流引擎
     *
     * @param context 内置表单内容
     * @param taskId  节点id
     */
    void noForm(String[] context, String taskId);

}
