package com.sdt.workflow.manager.service.impl;

import com.sdt.workflow.manager.service.ActivitiManagerService;
import com.sdt.workflow.person.vo.Person;
import com.sdt.workflow.utils.ActivitiUtil;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private ActivitiUtil activitiUtil;

    @Resource
    private HistoryService historyService;

    @Resource
    private HttpSession session;

    @Override
    public List<Deployment> findAllDeployments() {

        List<Deployment> list = repositoryService.createDeploymentQuery().orderByDeploymenTime().desc().list();
        return list;
    }

    @Override
    public List<ProcessDefinition> listAllBpmnList() {
        return repositoryService.createProcessDefinitionQuery().active().list();
    }

    @Override
    public List<Task> listMyTaskList() {

        Person person = (Person) session.getAttribute("person");
        return taskService.createTaskQuery().taskCandidateOrAssigned(person.getId()).list();
    }

    @Override
    public List<HistoricProcessInstance> listMyApply() {

        Person person = (Person) session.getAttribute("person");
        return historyService.createHistoricProcessInstanceQuery().startedBy(person.getId()).list();

    }

    @Override
    public List<ProcessInstance> listMyApplyUnOver() {
        Person person = (Person) session.getAttribute("person");
        return runtimeService.createProcessInstanceQuery().startedBy(person.getId()).list();
    }

    @Override
    public List<HistoricTaskInstance> listMyTaskOverList() {
        Person person = (Person) session.getAttribute("person");
        return historyService.createHistoricTaskInstanceQuery().taskCandidateUser(person.getId()).orderByHistoricTaskInstanceEndTime().desc().finished().list();
    }
}
