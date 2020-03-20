package com.sdt.workflow.person.controller;

import com.sdt.common.annotation.LoginComponent;
import com.sdt.common.result.Result;
import com.sdt.workflow.person.service.ActivitiPersonService;
import com.sdt.workflow.person.vo.Person;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 工作流中人员管理的接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@RestController
@LoginComponent
@RequestMapping("/workflow/person")
public class ActivitiPerson {

    @Resource
    private ActivitiPersonService activitiPersonService;

    @PostMapping("/addOrUpdate")
    public Result add(@RequestBody @Validated Person person) throws Exception {

        activitiPersonService.addOrUpdate(person);
        return Result.success("更新人员成功");
    }
}
