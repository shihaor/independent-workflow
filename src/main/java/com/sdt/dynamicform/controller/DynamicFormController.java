package com.sdt.dynamicform.controller;

import com.sdt.common.bean.PagerBean;
import com.sdt.common.result.Result;
import com.sdt.common.utils.CommonUtils;
import com.sdt.common.utils.JsonObjectMapper;
import com.sdt.dynamicform.displayform.IDisplayForm;
import com.sdt.dynamicform.entity.DynamicForm;
import com.sdt.dynamicform.entity.FormLinkBusiness;
import com.sdt.dynamicform.service.IDynamicFormsService;
import com.sdt.dynamicform.vo.ContainAllVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * 表单的接口类
 *
 * @author shihaoran
 * @date 2019/11/5
 */
@RestController
@Scope("prototype")
@RequestMapping("/form")
@Api(value = "动态表单接口", tags = {"动态表单业务"})
public class DynamicFormController {

    private final JsonObjectMapper mapper = new JsonObjectMapper();

    @Resource
    private IDynamicFormsService dynamicFormsService;

    @Resource
    @Qualifier("displayFormByProcess")
    private IDisplayForm displayFormByProcess;

    @Resource
    @Qualifier("displayFormByTask")
    private IDisplayForm displayFormByTask;

    @PostMapping(value = "/saveOrUpdateForm")
    @ApiOperation(value = "更新或者新增表单")
    public Result saveOrUpdateForm(@RequestBody ContainAllVO containAllVO) throws Exception {

        this.dynamicFormsService.saveOrUpdateForm(containAllVO);
        return Result.success("表单保存正常");
    }

    @GetMapping(value = "/obtainListForms")
    @ApiOperation(value = "分页查询全部的表单")
    public Result obtainListForms(PagerBean pagerBean) {

        List<DynamicForm> resultList = this.dynamicFormsService.obtainListForms();
        return CommonUtils.listResult(pagerBean, resultList);
    }

    @GetMapping(value = "/viewForm/{id}")
    @ApiOperation(value = "查询单条表单")
    public Result viewForm(@PathVariable("id") Integer id) throws Exception {

        DynamicForm form = this.dynamicFormsService.viewForm(id);
        String formData = form.getFormStyle();
        ContainAllVO containAllVO = mapper.readValue(formData, ContainAllVO.class);
        containAllVO.setId(id);
        return Result.success(containAllVO);
    }

    @PostMapping(value = "/deleteForm/{id}")
    @ApiOperation(value = "删除单条表单")
    public Result deleteForm(@PathVariable("id") Integer id) {

        this.dynamicFormsService.deleteForm(id);
        return Result.success("删除表单成功");
    }

    @GetMapping(value = "/displayStartForm/{id}")
    @ApiOperation(value = "展示第一个节点表单,第一次展示表单是不携带数据的")
    public Result displayStartForm(@PathVariable("id") String processDefineId) throws IOException {

        ContainAllVO result = (ContainAllVO) displayFormByProcess.displayForm(processDefineId);
        return Result.success(result);
    }

    @GetMapping(value = "/displayTaskForm")
    @ApiOperation(value = "展示办理过程中的节点表单")
    public Result displayTaskForm(String taskId) throws IOException {

        FormLinkBusiness result = (FormLinkBusiness) displayFormByTask.displayForm(taskId);
        return Result.success(result);
    }
}

