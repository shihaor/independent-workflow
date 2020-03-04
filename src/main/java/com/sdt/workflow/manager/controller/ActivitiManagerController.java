package com.sdt.workflow.manager.controller;

import com.sdt.common.bean.PagerBean;
import com.sdt.common.utils.JsonUtil;
import com.sdt.common.utils.PageUtil;
import com.sdt.workflow.manager.service.ActivitiManagerService;
import com.sdt.workflow.manager.vo.DeploymentVO;
import com.sdt.workflow.manager.vo.ProcessDefineVO;
import com.sdt.workflow.manager.vo.TaskVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @GetMapping(value = "/findAllDeployments", produces = "application/json;charset=UTF-8")
    public String findAllDeployments(PagerBean pagerBean) throws Exception {

        List<Deployment> deploymentList = activitiManagerService.findAllDeployments();
        List<DeploymentVO> resultList = new ArrayList<>();
        deploymentList.forEach(deployment -> {
            DeploymentVO deploymentVO = new DeploymentVO();
            deploymentVO.setId(deployment.getId());
            deploymentVO.setDeploymentTime(deployment.getDeploymentTime());
            deploymentVO.setName(deployment.getName());
            resultList.add(deploymentVO);
        });
        int startPage = pagerBean.getStartPage();
        int limit = pagerBean.getLimit();
        return JsonUtil.genJsonFromList(PageUtil.pageSubList(resultList, startPage, limit), resultList.size(), startPage, limit);
    }

    @ApiOperation(value = "获取我的申请")
    @GetMapping(value = "/listMyApplyList", produces = "application/json;charset=UTF-8")
    public String listMyApply(PagerBean pagerBean, HttpServletRequest request) throws Exception {
        List<HistoricProcessInstance> resultList = activitiManagerService.listMyApplyList(request);
        int startPage = pagerBean.getStartPage();
        int limit = pagerBean.getLimit();
        return JsonUtil.genJsonFromList(PageUtil.pageSubList(resultList, startPage, limit), resultList.size(), startPage, limit);
    }

    @GetMapping(value = "/listMyTaskList", produces = "application/json;charset=UTF-8")
    public String listMyTaskList(PagerBean pagerBean, HttpServletRequest request) throws Exception {

        List<Task> taskList = activitiManagerService.listMyTaskList(request);
        List<TaskVO> resultList = new ArrayList<>();
        taskList.forEach(task -> {
            TaskVO taskVO = new TaskVO();
            taskVO.setId(task.getId());
            taskVO.setName(task.getName());
            taskVO.setDescription(task.getDescription());
            resultList.add(taskVO);
        });
        int startPage = pagerBean.getStartPage();
        int limit = pagerBean.getLimit();
        return JsonUtil.genJsonFromList(PageUtil.pageSubList(resultList, startPage, limit), resultList.size(), startPage, limit);
    }

    @ResponseBody
    @GetMapping(value = "/listMyTaskOverList", produces = "application/json;charset=UTF-8")
    public String listMyTaskOver(PagerBean pagerBean, HttpServletRequest request) throws Exception {

        List<HistoricTaskInstance> resultList = activitiManagerService.listMyTaskOverList(request);
        int startPage = pagerBean.getStartPage();
        int limit = pagerBean.getLimit();
        return JsonUtil.genJsonFromList(PageUtil.pageSubList(resultList, startPage, limit), resultList.size(), startPage, limit);
    }

    @ApiOperation(value = "获取所有的部署定义")
    @GetMapping(value = "/listAllBpmnList", produces = "application/json;charset=UTF-8")
    public String listAllBpmnList(PagerBean pagerBean) throws Exception {
        List<ProcessDefinition> definitionList = activitiManagerService.listAllBpmnList();
        List<ProcessDefineVO> resultList = new ArrayList<>();
        definitionList.forEach(processDefinition -> {
            ProcessDefineVO processDefineVO = new ProcessDefineVO();
            processDefineVO.setId(processDefinition.getId());
            processDefineVO.setName(processDefinition.getName());
            processDefineVO.setDescription(processDefinition.getDescription());
            processDefineVO.setVersion(processDefinition.getVersion());
            resultList.add(processDefineVO);
        });
        int startPage = pagerBean.getStartPage();
        int limit = pagerBean.getLimit();
        return JsonUtil.genJsonFromList(PageUtil.pageSubList(resultList, startPage, limit), resultList.size(), startPage, limit);
    }
}
