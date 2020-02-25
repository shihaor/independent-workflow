package com.sdt.workflow.manager.controller;

import com.sdt.workflow.manager.service.ActivitiManagerService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
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

    @GetMapping("/contain")
    public String contain() {
        return "contain.html";
    }

    @GetMapping("/findAllDeployments")
    public String findAllDeployments(Model model) {

        List<Deployment> resultList = activitiManagerService.findAllDeployments();
        model.addAttribute("modelList", resultList);
        return "deployment.html";
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

    @GetMapping("/listMyTask")
    public String listMyTask(Model model) {

        List<Task> resultList = activitiManagerService.listMyTask();
        model.addAttribute("list", resultList);
        return "listMyTask.html";
    }

    @GetMapping("/listAllBpmn")
    public String listAllBpmn(Model model) {

        List<ProcessDefinition> resultList = activitiManagerService.listAllBpmn();
        model.addAttribute("list", resultList);
        return "listAllBpmn.html";
    }
}
