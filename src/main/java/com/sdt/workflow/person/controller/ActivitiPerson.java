package com.sdt.workflow.person.controller;

import com.sdt.workflow.annotation.LoginComponent;
import com.sdt.workflow.person.service.ActivitiPersonService;
import com.sdt.workflow.person.vo.Person;
import com.sdt.workflow.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 工作流中人员管理的接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Controller
@LoginComponent
@RequestMapping("/workflow/person")
public class ActivitiPerson {

    @Resource
    private ActivitiPersonService activitiPersonService;

    @PostMapping("/addOrUpdate")
    public String add(@RequestBody Person person) throws Exception {

        activitiPersonService.addOrUpdate(person);
        return JsonUtil.genJsonSuccess(true);
    }
}
