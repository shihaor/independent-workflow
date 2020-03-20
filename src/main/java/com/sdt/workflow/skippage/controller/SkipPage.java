package com.sdt.workflow.skippage.controller;

import io.swagger.annotations.ApiOperation;
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

    @GetMapping("/editor")
    @ApiOperation(value = "跳转至编辑页面")
    public String editor() {
        return "modeler.html";
    }

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

    @GetMapping("/listMyTask")
    public String listMyTask() {
        return "listMyTask.html";
    }

    @GetMapping("/listMyTaskOver")
    public String listMyTaskOver() {
        return "listMyTaskOver.html";
    }

    @GetMapping("/listAllBpmn")
    public String listAllBpmn() {
        return "listAllBpmn.html";
    }

    @GetMapping("/listMyApply")
    public String listMyApply() {
        return "listMyApply.html";
    }

    @GetMapping("/404")
    public String notFound() {
        return "404.html";
    }
}
