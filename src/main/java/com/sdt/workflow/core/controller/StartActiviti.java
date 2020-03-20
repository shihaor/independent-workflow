package com.sdt.workflow.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.common.result.Result;
import com.sdt.workflow.core.service.StartActivitiService;
import com.sdt.workflow.vo.ActivitiFormVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.form.FormProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "启动接口", tags = "启动接口")
public class StartActiviti {

    @Resource
    private StartActivitiService startActivitiService;

    @GetMapping(value = "/noForm/{id}")
    @ApiOperation(value = "根据流程定义id，使用内置表单启动")
    public Result<Object> noForm(@PathVariable("id") String processDefineId, HttpServletRequest request) throws Exception {

        HashMap<String, Object> map = startActivitiService.noForm(processDefineId, request);
        return Result.success(map);
    }

    @GetMapping(value = "/normalTask/{id}")
    @ApiOperation(value = "根据节点id完成指定节点，使用内置表单")
    public Result<Object> normalTask(@PathVariable("id") String taskId) {

        List<FormProperty> propertyList = startActivitiService.normalTask(taskId);
        return Result.success(propertyList);
    }

    @PostMapping(value = "/hasForm")
    @ApiOperation(value = "携带表单启动流程")
    public Result hasForm(@RequestBody ActivitiFormVO activitiFormVO, HttpServletRequest request) throws JsonProcessingException {
        startActivitiService.hasForm(activitiFormVO, request);
        return Result.success("流程携带表单启动成功");
    }

}
