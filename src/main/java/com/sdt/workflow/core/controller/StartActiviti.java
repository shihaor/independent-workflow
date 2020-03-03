package com.sdt.workflow.core.controller;

import com.sdt.workflow.core.service.StartActivitiService;
import org.activiti.engine.form.FormProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 启动接口
 * 1、没有其他表单组件启动，可以使用内部的表单
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Controller
@RequestMapping("/workflow/start")
public class StartActiviti {

    @Resource
    private StartActivitiService startActivitiService;

    @GetMapping("/noForm/{id}")
    public String noForm(@PathVariable("id") String processDefineId, Model model, HttpServletRequest request) throws Exception {

        HashMap<String, Object> map = startActivitiService.noForm(processDefineId, request);
        model.addAttribute("list", map.get("list"));
        model.addAttribute("taskId", map.get("taskId"));
        return "work.html";
    }

    @GetMapping("/normalTask/{id}")
    public String normalTask(@PathVariable("id") String taskId, Model model) throws Exception {

        List<FormProperty> propertyList = startActivitiService.normalTask(taskId);
        model.addAttribute("list", propertyList);
        model.addAttribute("taskId", taskId);
        return "work.html";
    }


}
