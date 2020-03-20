package com.sdt.dynamicform.dao;

import com.sdt.dynamicform.entity.FormLinkBusiness;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 查询{@link FormLinkBusiness}实体的dao
 *
 * @author shihaoran
 * @date 2019/11/13
 */
public interface IProcessValueDao extends CrudRepository<FormLinkBusiness, Integer>, JpaSpecificationExecutor, PagingAndSortingRepository<FormLinkBusiness, Integer> {

    /**
     * 通过流程实例id查找表单关联业务对象的实体
     *
     * @param processInstanceId 流程实例id
     * @return 表单关联业务对象的实体
     */
    FormLinkBusiness findByProcessInstanceId(String processInstanceId);
}
