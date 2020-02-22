package com.sdt.workflow.controller;

import com.sdt.workflow.service.ActivitiManagerService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @Autowired
    private final ActivitiManagerService activitiManagerService;

    public ActivitiManagerController(ActivitiManagerService activitiManagerService) {
        this.activitiManagerService = activitiManagerService;
    }

    @GetMapping("/findAllDeployments")
    public String findAllDeployments(Model model) {

        List<Deployment> resultList = activitiManagerService.findAllDeployments();
        model.addAttribute("modelList", resultList);
        return "deployment.html";
    }


}
