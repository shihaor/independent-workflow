package com.sdt.workflow.manager.controller;

import com.sdt.workflow.manager.GitLog;
import com.sdt.workflow.manager.service.IndexManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 首页管理接口
 *
 * @author shihaoran
 * @date 2020/2/25
 */
@Controller
@Api(value = "获取首页信息")
@RequestMapping("/index/manager")
public class IndexManager {

    @Resource
    private IndexManagerService indexManagerService;

    @GetMapping("/getIndexMsg")
    @ApiOperation(value = "获取首页信息")
    public String getIndexMsg(Model model) throws IOException, GitAPIException {

        int accountNumber = indexManagerService.getAccountNumber();
        int orgNumber = indexManagerService.getOrgNumber();
        int modelNumber = indexManagerService.getModelNumber();
        int deploymentNumber = indexManagerService.getDeploymentNumber();
        int runningNumber = indexManagerService.getRunningNumber();
        int overNumber = indexManagerService.getOverNumber();
        List<GitLog> commitLog = indexManagerService.getCommitLog();
        model.addAttribute("accountNumber", accountNumber);
        model.addAttribute("orgNumber", orgNumber);
        model.addAttribute("modelNumber", modelNumber);
        model.addAttribute("deploymentNumber", deploymentNumber);
        model.addAttribute("runningNumber", runningNumber);
        model.addAttribute("overNumber", overNumber);
        model.addAttribute("commitLog", commitLog);
        model.addAttribute("commitLogSize", commitLog.size());
        return "home.html";
    }
}
