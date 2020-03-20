package com.sdt.workflow.core.service.impl;

import com.sdt.workflow.core.service.CompleteActivitiService;
import com.sdt.workflow.person.vo.Person;
import com.sdt.workflow.vo.ActivitiFormVO;
import org.activiti.engine.FormService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

/**
 * 节点完成服务实现类
 *
 * @author shihaoran
 * @date 2020/2/24
 */
@Service
@Scope("prototype")
@Transactional(rollbackOn = Exception.class)
public class CompleteActivitiServiceImpl implements CompleteActivitiService {

    @Resource
    private TaskService taskService;

    @Resource
    private FormService formService;

    @Value("${session.userId}")
    private String user;

    @Override
    public Task noForm(String[] context, String taskId) {

        HashMap<String, Object> map = new HashMap<>(16);
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        List<FormProperty> propertyList = taskFormData.getFormProperties();
        for (int i = 0; i < propertyList.size(); i++) {
            map.put(propertyList.get(i).getId(), context[i]);
        }
        taskService.complete(taskId, map);
        return taskService.createTaskQuery().taskId(taskId).singleResult();
    }

    @Override
    public Task hasForm(ActivitiFormVO activitiFormVO, HttpServletRequest request) {

        String taskId = activitiFormVO.getTaskId();
        // 1、获取待完成的节点
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        // 2、将表单数据存入
        activitiFormVO.getFormData().forEach(formData -> taskService.setVariable(taskId, formData.getProp(), formData.getValue()));
        // 3、完成节点
        Person person = (Person) request.getSession().getAttribute(user);
        taskService.claim(taskId, person.getId());
        taskService.complete(taskId);
        return task;
    }
}
