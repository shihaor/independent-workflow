package com.sdt.workflow.core.controller;

import com.sdt.workflow.core.service.DeleteActivitiService;
import com.sdt.workflow.utils.JsonUtil;
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
public class DeleteActiviti {

    @Resource
    private DeleteActivitiService deleteActivitiService;

    @PostMapping("/deleteNoReason/{id}")
    public String deleteNoReason(@PathVariable("id") String processInstanceId) throws Exception {

        deleteActivitiService.deleteNoReason(processInstanceId);
        return JsonUtil.genJsonSuccess(true);
    }
}
