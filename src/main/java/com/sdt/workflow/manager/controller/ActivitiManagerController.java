package com.sdt.workflow.manager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.workflow.manager.service.ActivitiManagerService;
import com.sdt.workflow.manager.vo.DeploymentVO;
import com.sdt.workflow.manager.vo.ProcessDefineVO;
import com.sdt.workflow.manager.vo.TaskVO;
import com.sdt.workflow.utils.JsonUtil;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理工作流相关的接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Controller
@RequestMapping("/workflow")
public class ActivitiManagerController {

    @Resource
    private ActivitiManagerService activitiManagerService;

    @ResponseBody
    @GetMapping(value = "/findAllDeployments", produces = "application/json;charset=UTF-8")
    public String findAllDeployments() throws JsonProcessingException {

        List<Deployment> deploymentList = activitiManagerService.findAllDeployments();
        List<DeploymentVO> resultList = new ArrayList<>();
        deploymentList.forEach(deployment -> {
            DeploymentVO deploymentVO = new DeploymentVO();
            deploymentVO.setId(deployment.getId());
            deploymentVO.setDeploymentTime(deployment.getDeploymentTime());
            deploymentVO.setName(deployment.getName());
            resultList.add(deploymentVO);
        });
        return JsonUtil.genJsonList(resultList);
    }

    @ResponseBody
    @GetMapping(value = "/listMyApplyList", produces = "application/json;charset=UTF-8")
    public String listMyApply() throws JsonProcessingException {
        List<HistoricProcessInstance> resultList = activitiManagerService.listMyApplyList();
        return JsonUtil.genJsonList(resultList);
    }

    @ResponseBody
    @GetMapping(value = "/listMyTaskList", produces = "application/json;charset=UTF-8")
    public String listMyTaskList() throws JsonProcessingException {

        List<Task> taskList = activitiManagerService.listMyTaskList();
        List<TaskVO> resultList = new ArrayList<>();
        taskList.forEach(task -> {
            TaskVO taskVO = new TaskVO();
            taskVO.setId(task.getId());
            taskVO.setName(task.getName());
            taskVO.setDescription(task.getDescription());
            resultList.add(taskVO);
        });
        return JsonUtil.genJsonList(resultList);
    }

    @ResponseBody
    @GetMapping(value = "/listMyTaskOverList", produces = "application/json;charset=UTF-8")
    public String listMyTaskOver() throws JsonProcessingException {

        List<HistoricTaskInstance> resultList = activitiManagerService.listMyTaskOverList();
        return JsonUtil.genJsonList(resultList);
    }

    @ResponseBody
    @GetMapping(value = "/listAllBpmnList", produces = "application/json;charset=UTF-8")
    public String listAllBpmnList() throws JsonProcessingException {
        List<ProcessDefinition> definitionList = activitiManagerService.listAllBpmnList();
        List<ProcessDefineVO> resultList = new ArrayList<>();
        definitionList.forEach(processDefinition -> {
            ProcessDefineVO processDefineVO = new ProcessDefineVO();
            processDefineVO.setId(processDefinition.getId());
            processDefineVO.setName(processDefinition.getName());
            processDefineVO.setDescription(processDefinition.getDescription());
            processDefineVO.setVersion(processDefinition.getVersion());
            resultList.add(processDefineVO);
        });
        return JsonUtil.genJsonList(resultList);
    }
}
