package com.sdt.dynamicform.dao;

import com.sdt.dynamicform.entity.BusinessObject;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 业务对象dao
 *
 * @date 2019/11/11
 * @author shihaoran
 */
public interface IBusinessObjectDao extends JpaRepository<BusinessObject, Integer> {

    /**
     * 通过流程定义id查找业务对象
     *
     * @param processDefineId 流程定义id
     * @return 业务对象
     */
    BusinessObject findByProcessDefineIdAndDeletedIsFalse(String processDefineId);
}
