package com.sdt.dynamicform.dao;

import com.sdt.dynamicform.entity.DynamicForm;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 动态表单的dao
 *
 * @author shihaoran
 * @date 2019/11/5
 */
public interface IDynamicFormDao extends CrudRepository<DynamicForm, Integer>, JpaSpecificationExecutor,
        PagingAndSortingRepository<DynamicForm, Integer> {

    /**
     * 通过业务id来查找
     *
     * @param businessId 业务对象id
     * @return 表单
     */
    DynamicForm findByBusinessId(Integer businessId);
}
