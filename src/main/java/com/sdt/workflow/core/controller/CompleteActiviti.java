package com.sdt.workflow.core.controller;

import com.sdt.workflow.core.service.CompleteActivitiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 完成节点的接口
 * 1、noForm 无表单完成
 *
 * @author shihaoran
 * @date 2020/2/24
 */
@Controller
@RequestMapping("/workflow/complete")
public class CompleteActiviti {

    @Resource
    private CompleteActivitiService completeActivitiService;

    @PostMapping("/noForm")
    public void noForm(HttpServletRequest request) {
        //接收表单填写内容
        String[] context = request.getParameterValues("context");
        //流程实例id
        String taskId = request.getParameter("task");

        completeActivitiService.noForm(context, taskId);
    }
}
