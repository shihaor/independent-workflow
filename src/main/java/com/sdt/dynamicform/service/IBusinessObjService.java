package com.sdt.dynamicform.service;

import com.sdt.common.bean.PagerBean;
import com.sdt.dynamicform.entity.BusinessObject;

import java.util.List;

/**
 * 业务对象服务接口
 *
 * @author shihaoran
 * @date 2020/3/14
 */
public interface IBusinessObjService {

    /**
     * 保存或者修改业务对象
     *
     * @param businessObject 业务对象
     */
    void addOrUpdate(BusinessObject businessObject) throws Exception;

    /**
     * 通过id查找
     *
     * @param id 业务对象id
     * @return 业务对象
     */
    BusinessObject viewBusiness(Integer id);

    /**
     * 通过id删除
     *
     * @param id 业务对象id
     */
    void deleteBusiness(Integer id);

    /**
     * 条件查询展示列表
     *
     * @param pagerBean      分页参数
     * @param businessObject 查询条件
     * @return 业务对象列表
     */
    List<BusinessObject> obtainBusiness(PagerBean pagerBean, BusinessObject businessObject);
}
