package com.sdt.workflow.person.controller;

import com.sdt.common.annotation.LoginComponent;
import com.sdt.common.result.Result;
import com.sdt.workflow.person.service.PersonManagerService;
import com.sdt.workflow.person.vo.Person;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 人员登录注销等接口
 *
 * @author shihaoran
 * @date 2020/2/23
 */
@RestController
@LoginComponent
@RequestMapping("/user")
public class PersonManager {

    @Resource
    private PersonManagerService personManagerService;

    @PostMapping(value = "/check")
    public Result check(@RequestParam("id") String id, @RequestParam("password") String password, HttpServletRequest request) throws Exception {

        Person person = new Person();
        person.setId(id);
        person.setPassword(password);
        Person result = personManagerService.checkPassword(person, request);
        return Result.success(result);
    }

    @GetMapping("/out")
    public Result out(HttpServletRequest request) throws Exception {

        request.getSession().removeAttribute("person");
        return Result.success("登出成功");
    }

}
