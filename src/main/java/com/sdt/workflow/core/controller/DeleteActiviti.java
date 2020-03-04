package com.sdt.workflow.core.controller;

import com.sdt.workflow.core.service.DeleteActivitiService;
import com.sdt.common.utils.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 删除已经启动的流程实例
 *
 * @author shihaoran
 * @date 2020/2/26
 */
@RestController
@RequestMapping("/workflow/delete")
@Api(value = "删除已经启动的流程实例", tags = "删除已经启动的流程实例")
public class DeleteActiviti {

    @Resource
    private DeleteActivitiService deleteActivitiService;

    @PostMapping("/deleteNoReason/{id}")
    @ApiOperation(value = "根据流程实例id删除流程，需要删除原因")
    public String deleteNoReason(@PathVariable("id") String processInstanceId) throws Exception {

        deleteActivitiService.deleteNoReason(processInstanceId);
        return JsonUtil.genJsonSuccess(true);
    }
}
