package com.sdt.workflow.manager.controller;

import com.sdt.common.bean.PagerBean;
import com.sdt.common.constant.DatePattern;
import com.sdt.common.result.Result;
import com.sdt.common.utils.CommonUtils;
import com.sdt.common.vo.CommonVO;
import com.sdt.workflow.manager.service.ActivitiManagerService;
import com.sdt.workflow.manager.vo.ProcessDefineVO;
import com.sdt.workflow.manager.vo.TaskVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理工作流相关的接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@RestController
@RequestMapping("/workflow")
@Api(value = "管理工作流相关的接口", tags = "管理工作流相关的接口")
public class ActivitiManagerController {

    @Resource
    private ActivitiManagerService activitiManagerService;

    @ApiOperation(value = "获取所有未挂起的部署定义")
    @PostMapping(value = "/findAllDeployments")
    public Result findAllDeployments(PagerBean pagerBean) {

        List<ProcessDefineVO> resultList = activitiManagerService.findAllDeployments(pagerBean);
        return CommonUtils.listResult(pagerBean, resultList);
    }

    @ApiOperation(value = "获取我的申请")
    @PostMapping(value = "/obtainMyApplyList")
    public Result obtainMyApplyList(PagerBean pagerBean, HttpServletRequest request) {
        List<HistoricProcessInstance> resultList = activitiManagerService.obtainMyApplyList(request, pagerBean);
        return CommonUtils.listResult(pagerBean, resultList);
    }

    @ApiOperation(value = "获取我的待办")
    @PostMapping(value = "/obtainMyTaskList")
    public Result obtainMyTaskList(PagerBean pagerBean, HttpServletRequest request) {

        List<Task> taskList = activitiManagerService.obtainMyTaskList(request, pagerBean);
        List<TaskVO> resultList = new ArrayList<>();
        taskList.forEach(task -> {
            TaskVO taskVO = new TaskVO();
            taskVO.setId(task.getId());
            taskVO.setStartTime(CommonUtils.dateToString(task.getCreateTime(), DatePattern.DATE_ALL));
            taskVO.setName(task.getName());
            taskVO.setDescription(task.getDescription());
            resultList.add(taskVO);
        });
        return CommonUtils.listResult(pagerBean, resultList);
    }

    @ApiOperation(value = "获取我办理过的流程")
    @PostMapping(value = "/obtainMyTaskOverList")
    public Result obtainMyTaskOverList(PagerBean pagerBean, HttpServletRequest request) {

        List<HistoricTaskInstance> resultList = activitiManagerService.obtainMyTaskOverList(request, pagerBean);
        return CommonUtils.listResult(pagerBean, resultList);
    }

    @ApiOperation(value = "获取所有的部署定义")
    @PostMapping(value = "/obtainAllBpmnList")
    public Result obtainAllBpmnList(PagerBean pagerBean) {
        List<ProcessDefineVO> resultList = activitiManagerService.obtainAllBpmnList(pagerBean);
        return CommonUtils.listResult(pagerBean, resultList);
    }

    @ApiOperation(value = "获取流程所有的节点")
    @PostMapping(value = "/obtainAllTaskName/{processDefineId}")
    public Result obtainAllTaskName(@PathVariable("processDefineId") String processDefineId) {
        List<CommonVO> result = activitiManagerService.obtainAllTaskName(processDefineId);
        return Result.success(result);
    }
}
