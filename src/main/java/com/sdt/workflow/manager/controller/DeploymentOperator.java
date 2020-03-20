package com.sdt.workflow.manager.controller;

import com.sdt.common.obtainpng.IObtainPng;
import com.sdt.common.result.Result;
import com.sdt.workflow.manager.service.DeploymentOperatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Resource
    @Qualifier("ObtainPngByProcessDefineId")
    private IObtainPng ObtainPngByProcessDefineId;

    @ApiOperation("挂起部署定义")
    @PostMapping("/suspendDeployment/{id}")
    public Result suspendDeployment(@PathVariable("id") String id) {

        deploymentOperatorService.suspendDeployment(id);
        return Result.success("挂起成功，流程将不能再启动");
    }

    @ApiOperation("激活部署定义")
    @PostMapping("/activeDeployment/{id}")
    public Result activeDeployment(@PathVariable("id") String id) {

        deploymentOperatorService.activeDeployment(id);
        return Result.success("激活成功，流程将可以启动");
    }

    @ApiOperation("删除部署定义")
    @PostMapping("/deleteDeployment/{id}")
    public Result deleteDeployment(@PathVariable("id") String id) {

        deploymentOperatorService.deleteDeployment(id);
        return Result.success("删除流程定义成功");
    }

    @ApiOperation("获取部署定义图片")
    @GetMapping("/getBpmn/{id}")
    public void getBpmn(@PathVariable("id") String id, HttpServletResponse response) throws IOException {

        byte[] source = ObtainPngByProcessDefineId.obtainPngByte(id);
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.write(source);
            outputStream.flush();
        }
    }
}
