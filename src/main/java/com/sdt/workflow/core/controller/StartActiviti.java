package com.sdt.workflow.core.controller;

import com.sdt.workflow.core.service.StartActivitiService;
import org.activiti.engine.form.FormProperty;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 启动接口
 * 1、没有其他表单组件启动，可以使用内部的表单
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@RestController
@RequestMapping("/workflow/start")
public class StartActiviti {

    @Resource
    private StartActivitiService startActivitiService;

    @PostMapping("/noForm/{id}")
    public String noForm(@PathVariable("id") String processDefineId, Model model) throws Exception {

        List<FormProperty> propertyList = startActivitiService.noForm(processDefineId);
        model.addAttribute("list", propertyList);
        return "work.html";
    }
}
