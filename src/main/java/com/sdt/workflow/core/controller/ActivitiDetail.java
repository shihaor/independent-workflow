package com.sdt.workflow.core.controller;

import com.sdt.workflow.core.service.ActivitiDetailService;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.ActivitiException;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 工作流获取相关细节
 *
 * @author shihaoran
 * @date 2020/3/4
 */
@RestController
@RequestMapping("/workflow")
public class ActivitiDetail {

    @Resource
    private ActivitiDetailService activitiDetailService;

    @GetMapping("/obtainPng/{processInstanceId}")
    @ApiOperation(value = "返回当前节点高亮的流程图片,流程实例id不能为空")
    public void obtainPng(@PathVariable("processInstanceId") String processInstanceId, HttpServletResponse response) throws Exception {

        Assert.notNull(processInstanceId, "流程实例id不能为空");
        // 输出资源内容到相应对象
        response.setCharacterEncoding("UTF-8");
        response.setContentType("image/png");
        byte[] bytes = new byte[512];
        int temp;
        try (
                InputStream imageStream = activitiDetailService.obtainProcessNowNodePng(processInstanceId);
                ServletOutputStream outputStream = response.getOutputStream()
        ) {
            while ((temp = imageStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, temp);
            }
        } catch (IOException e) {
            throw new ActivitiException("流程图的流无法正常关闭");
        }
    }
}
