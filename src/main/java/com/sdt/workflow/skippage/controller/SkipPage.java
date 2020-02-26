package com.sdt.workflow.skippage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面跳转的接口，方便日后前后端分离
 *
 * @author shihaoran
 * @date 2020/2/26
 */
@Controller
public class SkipPage {

    @GetMapping("/loginPage")
    public String loginPage() {
        return "login.html";
    }

    @GetMapping("/contain")
    public String contain() {
        return "contain.html";
    }

    @GetMapping("/fontList")
    public String fontList() {
        return "fontList.html";
    }

    @GetMapping("/deployment")
    public String deployment() {
        return "deployment.html";
    }
}
