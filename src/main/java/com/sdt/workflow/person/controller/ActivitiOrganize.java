package com.sdt.workflow.person.controller;

import com.sdt.workflow.annotation.LoginComponent;
import com.sdt.workflow.person.service.ActivitiOrganizeService;
import com.sdt.workflow.person.vo.Organize;
import com.sdt.common.utils.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 工作流机构管理
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Controller
@LoginComponent
@RequestMapping("/org")
public class ActivitiOrganize {

    @Resource
    private ActivitiOrganizeService activitiOrganizeService;

    @PostMapping("/addOrUpdate")
    public String add(@RequestBody Organize organize) throws Exception {

        activitiOrganizeService.addOrUpdate(organize);
        return JsonUtil.genJsonSuccess(true);
    }
}
