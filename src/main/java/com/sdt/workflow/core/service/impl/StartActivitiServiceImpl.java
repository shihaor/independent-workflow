package com.sdt.workflow.core.service.impl;

import com.sdt.workflow.core.service.StartActivitiService;
import com.sdt.workflow.person.vo.Person;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    private HttpServletRequest request;

    @Override
    public void noForm(String deploymentId) {
        // 需要从session中获取人员
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        Person person = (Person) request.getSession().getAttribute("person");
        identityService.setAuthenticatedUserId(person.getId());
    }
}
