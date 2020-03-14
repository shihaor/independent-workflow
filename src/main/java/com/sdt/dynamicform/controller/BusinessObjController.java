package com.sdt.dynamicform.controller;

import com.sdt.common.bean.PagerBean;
import com.sdt.dynamicform.entity.BusinessObject;
import com.sdt.dynamicform.service.IBusinessObjService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String addOrUpdate(BusinessObject businessObject) throws Exception {
        processBusiness.addOrUpdate(businessObject);
        return businessObject.toString() + "保存成功";
    }

    @GetMapping("/viewBusiness")
    @ApiOperation(value = "查找业务对象")
    public String viewBusiness(Integer id) {
        BusinessObject result = processBusiness.viewBusiness(id);
        return result.toString();
    }

    @GetMapping("/deleteBusiness")
    @ApiOperation(value = "删除业务对象")
    public String deleteBusiness(Integer id) {
        processBusiness.deleteBusiness(id);
        return "删除成功";
    }

    @PostMapping("/obtainBusiness")
    @ApiOperation(value = "删除业务对象")
    public List<BusinessObject> obtainBusiness(PagerBean pagerBean, BusinessObject businessObject) {
        List<BusinessObject> resultList = processBusiness.obtainBusiness(pagerBean, businessObject);
        return resultList;
    }
}
