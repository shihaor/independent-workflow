package com.sdt.workflow.manager.service.impl;

import com.sdt.workflow.manager.service.ActivitiManagerService;
import com.sdt.workflow.person.vo.Person;
import com.sdt.workflow.utils.ActivitiUtil;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 工作流管理服务实现类
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Service
@Scope("prototype")
public class ActivitiManagerServiceImpl implements ActivitiManagerService {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private TaskService taskService;

    @Resource
    private ActivitiUtil activitiUtil;

    @Resource
    private HttpServletRequest request;

    @Override
    public List<Deployment> findAllDeployments() {

        List<Deployment> list = repositoryService.createDeploymentQuery().orderByDeploymenTime().desc().list();
        return list;
    }

    @Override
    public List<ProcessDefinition> listAllBpmn() {
        return repositoryService.createProcessDefinitionQuery().active().list();
    }

    @Override
    public List<Task> listMyTask() {

        Person person = (Person) request.getSession().getAttribute("person");
        return taskService.createTaskQuery().taskCandidateOrAssigned(person.getId()).list();
    }
}
