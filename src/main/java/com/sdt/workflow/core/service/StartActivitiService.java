package com.sdt.workflow.core.service;

import org.activiti.engine.form.FormProperty;

import java.util.HashMap;
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
     * @return 节点中的表单数据 和 节点id
     */
    HashMap<String, Object> noForm(String processDefineId);

    /**
     * 通过taskId获取当前节点的表单
     *
     * @param taskId
     * @return
     */
    List<FormProperty> normalTask(String taskId);
}
