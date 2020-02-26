package com.sdt.workflow.person.controller;

import com.sdt.workflow.annotation.LoginComponent;
import com.sdt.workflow.person.service.PersonManagerService;
import com.sdt.workflow.person.vo.Person;
import com.sdt.workflow.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 人员登录注销等接口
 *
 * @author shihaoran
 * @date 2020/2/23
 */
@Controller
@LoginComponent
@RequestMapping("/user")
public class PersonManager {

    @Resource
    private PersonManagerService personManagerService;

    @Resource
    private HttpSession session;

    @PostMapping("/check")
    public String check(@RequestBody Person person) throws Exception {

        personManagerService.checkPassword(person);
        return JsonUtil.genJsonSuccess(true);
    }

    @GetMapping("/out")
    public String out() {

        session.removeAttribute("person");
        return "login.html";
    }

}
