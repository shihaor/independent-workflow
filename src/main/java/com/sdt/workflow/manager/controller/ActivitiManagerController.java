package com.sdt.workflow.manager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.workflow.manager.service.ActivitiManagerService;
import com.sdt.workflow.manager.vo.DeploymentVO;
import com.sdt.workflow.utils.JsonUtil;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/listMyApply")
    public String listMyApply(Model model) {
        List<HistoricProcessInstance> resultList = activitiManagerService.listMyApply();
        model.addAttribute("list", resultList);
        return "listMyApply.html";
    }

    @GetMapping("/listMyApply/unOver")
    public String listMyApplyUnOver(Model model) {
        List<ProcessInstance> resultList = activitiManagerService.listMyApplyUnOver();
        model.addAttribute("list", resultList);
        return "listMyApply.html";
    }

    @ResponseBody
    @GetMapping(value = "/listMyTaskList", produces = "application/json;charset=UTF-8")
    public String listMyTaskList() throws JsonProcessingException {

        List<Task> resultList = activitiManagerService.listMyTaskList();
        return JsonUtil.genJsonList(resultList);
    }

    @ResponseBody
    @GetMapping(value = "/listMyTaskOverList", produces = "application/json;charset=UTF-8")
    public String listMyTaskOver() throws JsonProcessingException {

        List<HistoricTaskInstance> resultList = activitiManagerService.listMyTaskOverList();
        return JsonUtil.genJsonList(resultList);
    }

    @GetMapping("/listAllBpmn")
    public String listAllBpmn(Model model) {

        List<ProcessDefinition> resultList = activitiManagerService.listAllBpmn();
        model.addAttribute("list", resultList);
        return "listAllBpmn.html";
    }
}
