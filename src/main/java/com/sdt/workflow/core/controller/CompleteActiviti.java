package com.sdt.workflow.core.controller;

import com.sdt.common.result.Result;
import com.sdt.workflow.core.service.CompleteActivitiService;
import com.sdt.workflow.vo.ActivitiFormVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(value = "完成节点的接口", tags = "完成节点的接口")
public class CompleteActiviti {

    @Resource
    private CompleteActivitiService completeActivitiService;

    @PostMapping(value = "/noForm")
    @ApiOperation(value = "无表单完成，即使用内置表单")
    public Result<Object> noForm(HttpServletRequest request) {
        //接收表单填写内容
        String[] context = request.getParameterValues("context");
        //流程实例id
        String taskId = request.getParameter("task");

        Task task = completeActivitiService.noForm(context, taskId);
        return Result.success(task.getName() + "节点完成");
    }

    @ApiOperation("有表单完成")
    @PostMapping(value = "/hasForm")
    public Result hasForm(@RequestBody ActivitiFormVO activitiFormVO, HttpServletRequest request) {
        Task task = completeActivitiService.hasForm(activitiFormVO, request);
        return Result.success(task.getName() + "节点完成");
    }

}
