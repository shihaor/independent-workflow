package com.sdt.workflow.utils;

import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工作流的一些小工具
 *
 * @author shihaoran
 * @date 2020/2/24
 */
@Component
public class ActivitiUtil {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private FormService formService;

    /**
     * 通过节点id获取工作流实例
     *
     * @param taskId 节点id
     * @return 工作流实例
     */
    public ProcessInstance task2ProcessInstance(String taskId) {

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        return processInstance;
    }

    /**
     * 通过工作流实例id获取当前节点
     *
     * @param processInstanceId 实例id
     * @return 当前节点
     */
    public Task ProcessInstance2task(String processInstanceId) {

        return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
    }

    /**
     * 获取内置表单的内容
     *
     * @param taskId 节点id
     * @return 内置表单的内容
     */
    public List<FormProperty> getFormProperties(String taskId) {
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        return taskFormData.getFormProperties();
    }
}
