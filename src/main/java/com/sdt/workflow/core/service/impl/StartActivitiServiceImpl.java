package com.sdt.workflow.core.service.impl;

import com.sdt.workflow.core.service.StartActivitiService;
import com.sdt.workflow.person.vo.Person;
import com.sdt.workflow.utils.ActivitiUtil;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
    private ActivitiUtil activitiUtil;

    @Resource
    private HttpSession session;

    @Override
    public HashMap<String, Object> noForm(String processDefineId) {
        HashMap<String, Object> map = new HashMap<>(2);
        // 需要从session中获取人员
        Person person = (Person) session.getAttribute("person");
        // 将流程绑定到流程的启动者身上
        identityService.setAuthenticatedUserId(person.getId());
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefineId);
        // 添加启动人
        repositoryService.addCandidateStarterUser(processDefineId, person.getId());
        // 如果有内置表单，将这个节点的内置表单返回给前端
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        taskService.claim(task.getId(), person.getId());
        // 获取内置表单
        List<FormProperty> propertyList = activitiUtil.getFormProperties(task.getId());
        map.put("list", propertyList);
        map.put("taskId", task.getId());
        return map;
    }

    @Override
    public List<FormProperty> normalTask(String taskId) {
        return activitiUtil.getFormProperties(taskId);
    }
}
