package com.sdt.dynamicform.controller;

import com.sdt.common.bean.PagerBean;
import com.sdt.common.utils.JsonObjectMapper;
import com.sdt.common.utils.JsonUtil;
import com.sdt.common.utils.PageUtil;
import com.sdt.dynamicform.entity.DynamicForm;
import com.sdt.dynamicform.service.IDynamicFormsService;
import com.sdt.dynamicform.vo.ContainAllVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    @PostMapping(value = "/saveOrUpdateForm")
    @ApiOperation(value = "更新或者新增表单")
    public String saveOrUpdateForm(@RequestBody ContainAllVO containAllVO) throws Exception {

        this.dynamicFormsService.saveOrUpdateForm(containAllVO);
        return JsonUtil.genJsonSuccess("表单保存正常");
    }

    @GetMapping(value = "/obtainListForms")
    @ApiOperation(value = "分页查询全部的表单")
    public String obtainListForms(PagerBean pagerBean) throws Exception {

        List<DynamicForm> resultList = this.dynamicFormsService.obtainListForms();
        int startPage = pagerBean.getStartPage();
        int limit = pagerBean.getLimit();
        return JsonUtil.genJsonFromList(PageUtil.pageSubList(resultList, startPage, limit), resultList.size(), startPage, limit);
    }

    @GetMapping(value = "/viewForm")
    @ApiOperation(value = "查询单条表单")
    public String viewForm(@RequestParam("id") Integer id) throws Exception {

        DynamicForm form = this.dynamicFormsService.viewForm(id);
        String formData = form.getFormData();
        ContainAllVO containAllVO = mapper.readValue(formData, ContainAllVO.class);
        containAllVO.setId(id);
        return JsonUtil.genJsonSuccess(containAllVO);
    }

    @PostMapping(value = "/deleteForm")
    @ApiOperation(value = "删除单条表单")
    public String deleteForm(@RequestParam("id") Integer id) throws Exception {

        this.dynamicFormsService.deleteForm(id);
        return JsonUtil.genJsonSuccess("删除成功");
    }
}

