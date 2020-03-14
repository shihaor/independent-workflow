package com.sdt.dynamicform.service.impl;

import com.sdt.common.bean.PagerBean;
import com.sdt.dynamicform.dao.IBusinessObjectDao;
import com.sdt.dynamicform.entity.BusinessObject;
import com.sdt.dynamicform.service.IBusinessObjService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 方便业务定义拓展，拓展流程定义继承此类
 *
 * @author shihaoran
 * @date 2020/3/14
 */
public abstract class BusinessObjServiceImpl implements IBusinessObjService {

    @Resource
    protected IBusinessObjectDao businessObjectDao;

    @Override
    public BusinessObject viewBusiness(Integer id) {

        return businessObjectDao.findOne(id);
    }

    @Override
    public void deleteBusiness(Integer id) {
        businessObjectDao.delete(id);
    }

    @Override
    public List<BusinessObject> obtainBusiness(PagerBean pagerBean, BusinessObject businessObject) {
        return businessObjectDao.findAll();
    }
}
