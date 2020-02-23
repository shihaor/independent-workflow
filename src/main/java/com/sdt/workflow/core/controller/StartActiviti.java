package com.sdt.workflow.core.controller;

import com.sdt.workflow.core.service.StartActivitiService;
import com.sdt.workflow.utils.JsonUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 启动接口
 * 1、没有表单启动
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
    public String noForm(@PathVariable("id") String deploymentId) throws Exception {

        startActivitiService.noForm(deploymentId);
        return JsonUtil.genJsonSuccess(true);
    }
}
