package com.sdt.workflow.core.service;

import org.activiti.engine.form.FormProperty;

import java.util.List;

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
     * @param processDefineId 流程定义id
     * @return 节点中的表单数据
     */
    List<FormProperty> noForm(String processDefineId);
}
