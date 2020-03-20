package com.sdt.workflow.manager.controller;

import com.sdt.common.result.Result;
import com.sdt.workflow.manager.GitLog;
import com.sdt.workflow.manager.service.IndexManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 首页管理接口
 *
 * @author shihaoran
 * @date 2020/2/25
 */
@RestController
@Api(value = "获取首页信息")
@RequestMapping("/index/manager")
public class IndexManager {

    @Resource
    private IndexManagerService indexManagerService;

    @GetMapping(value = "/getIndexMsg")
    @ApiOperation(value = "获取首页信息")
    public Result getIndexMsg() throws Exception {

        int accountNumber = indexManagerService.getAccountNumber();
        int orgNumber = indexManagerService.getOrgNumber();
        int modelNumber = indexManagerService.getModelNumber();
        int deploymentNumber = indexManagerService.getDeploymentNumber();
        int runningNumber = indexManagerService.getRunningNumber();
        int overNumber = indexManagerService.getOverNumber();
        List<GitLog> commitLog = indexManagerService.getCommitLog();
        List<Object> list = new ArrayList<>();
        HashMap<Object, Object> map = new HashMap<>(16);
        map.put("accountNumber",accountNumber );
        map.put("orgNumber",orgNumber );
        map.put("modelNumber", modelNumber);
        map.put("deploymentNumber", deploymentNumber);
        map.put("runningNumber", runningNumber);
        map.put("overNumber", overNumber);
        map.put("commitLog", commitLog);
        map.put("commitLogSize", commitLog.size());
        list.add(map);
        return Result.success(list);
    }
}
