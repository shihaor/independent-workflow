package com.sdt.workflow.manager.controller;

import com.sdt.common.utils.JsonUtil;
import com.sdt.workflow.manager.service.DeploymentOperatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * 对已部署资源的操作接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@RestController
@RequestMapping("/operator")
@Api(value = "对已部署资源的操作接口", tags = "对已部署资源的操作接口")
public class DeploymentOperator {

    @Resource
    private DeploymentOperatorService deploymentOperatorService;

    @ApiOperation("挂起部署定义")
    @PostMapping("/suspendDeployment/{id}")
    public String suspendDeployment(@PathVariable("id") String id) throws Exception {

        deploymentOperatorService.suspendDeployment(id);
        return JsonUtil.genJsonSuccess(true);
    }

    @ApiOperation("激活部署定义")
    @PostMapping("/activeDeployment/{id}")
    public String activeDeployment(@PathVariable("id") String id) throws Exception {

        deploymentOperatorService.activeDeployment(id);
        return JsonUtil.genJsonSuccess(true);
    }

    @ApiOperation("删除部署定义")
    @PostMapping("/deleteDeployment/{id}")
    public String deleteDeployment(@PathVariable("id") String id) throws Exception {

        deploymentOperatorService.deleteDeployment(id);
        return JsonUtil.genJsonSuccess(true);
    }

    @ApiOperation("获取部署定义图片")
    @GetMapping("/getBpmn/{id}")
    public void getBpmn(@PathVariable("id") String id, HttpServletResponse response) throws Exception {

        byte[] source = deploymentOperatorService.getBpmn(id);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(source);
            outputStream.flush();
        }
    }
}
