package com.sdt.dynamicform.controller;

import com.sdt.common.bean.PagerBean;
import com.sdt.common.handler.LogDocumentary;
import com.sdt.common.result.Result;
import com.sdt.common.utils.CommonUtils;
import com.sdt.dynamicform.entity.BusinessObject;
import com.sdt.dynamicform.service.IBusinessObjService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shihaoran
 * @date 2020/3/14
 */
@RestController
@RequestMapping("business")
@Api(value = "业务对象操作接口", tags = "业务对象操作接口")
public class BusinessObjController {

    @Resource
    @Qualifier(value = "processBusiness")
    private IBusinessObjService processBusiness;


    @PostMapping("/addOrUpdate")
    @ApiOperation(value = "添加或者更新业务对象")
    public Result addOrUpdate(BusinessObject businessObject) throws Exception {
        BusinessObject result = processBusiness.addOrUpdate(businessObject);
        return Result.success(result.getBusinessName() + "保存成功");
    }

    @LogDocumentary(action = "查找业务对象")
    @GetMapping("/viewBusiness/{id}")
    @ApiOperation(value = "查找业务对象")
    public Result viewBusiness(@PathVariable("id") Integer id) {
        BusinessObject result = processBusiness.viewBusiness(id);
        return Result.success(result);
    }

    @GetMapping("/deleteBusiness/{id}")
    @ApiOperation(value = "删除业务对象")
    public Result deleteBusiness(@PathVariable("id") Integer id) {
        processBusiness.deleteBusiness(id);
        return Result.success("删除业务对象成功");
    }

    @PostMapping("/obtainBusiness")
    @ApiOperation(value = "获取业务对象列表")
    public Result obtainBusiness(PagerBean pagerBean, BusinessObject businessObject) {
        List<BusinessObject> resultList = processBusiness.obtainBusiness(pagerBean, businessObject);
        return CommonUtils.listResult(pagerBean, resultList);
    }
}
