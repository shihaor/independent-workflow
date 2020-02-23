package com.sdt.workflow.manager.controller;

import com.sdt.workflow.manager.service.ActivitiManagerService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
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

    @GetMapping("/findAllDeployments")
    public String findAllDeployments(Model model) {

        List<Deployment> resultList = activitiManagerService.findAllDeployments();
        model.addAttribute("modelList", resultList);
        return "deployment.html";
    }

    @GetMapping("/contain")
    public String contain() {
        return "contain.html";
    }

    @GetMapping("/listAllBpmn")
    public String listAllBpmn(Model model) {

        List<ProcessDefinition> resultList = activitiManagerService.listAllBpmn();
        model.addAttribute("list", resultList);
        return "allBpmn.html";
    }
}
