package com.sdt.workflow.core.service.impl;

import com.sdt.workflow.core.service.StartActivitiService;
import com.sdt.workflow.person.vo.Person;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工作流启动实现类
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Service
@Scope("prototype")
public class StartActivitiServiceImpl implements StartActivitiService {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private IdentityService identityService;

    @Resource
    private FormService formService;

    @Resource
    private HttpServletRequest request;

    @Override
    public List<FormProperty> noForm(String processDefineId) {
        // 需要从session中获取人员
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefineId);
        Person person = (Person) request.getSession().getAttribute("person");
        // 将流程绑定到流程的启动者身上
        identityService.setAuthenticatedUserId(person.getId());
        // 添加启动人
        repositoryService.addCandidateStarterUser(processDefineId, person.getId());
        // 如果有内置表单，将这个节点的内置表单返回给前端
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> propertyList = taskFormData.getFormProperties();
        return propertyList;
    }
}
