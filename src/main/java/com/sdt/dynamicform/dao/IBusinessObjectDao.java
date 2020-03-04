package com.sdt.dynamicform.dao;

import com.sdt.dynamicform.entity.BusinessObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 业务对象dao
 *
 * @date 2019/11/11
 * @author shihaoran
 */
public interface IBusinessObjectDao extends JpaRepository<BusinessObject, Integer> {

    /**
     * 通过业务对象名字查询
     *
     * @param processName 业务对象名字
     * @return {@link BusinessObject}业务对象
     * @throws Exception 查询异常
     */
    List<BusinessObject> findAllByBusinessName(String processName) throws Exception;

    /**
     * 获取全部的业务对象，绑定formId的除外
     *
     * @return 业务对象集合
     * @throws Exception 查询异常
     * @param type 查找的类型
     */
    @Query(value = "select from BusinessObject o where o.formId is null and o.type is :types", nativeQuery = true)
    List<BusinessObject> findAllBusinessObj(String type) throws Exception;

    /**
     * 根据流程定义id删除业务对象
     *
     * @param processDefId 流程定义Id
     */
    void deleteByProcessDefId(String processDefId) throws Exception;

    /**
     * 根据业务对象名字获取业务对象
     *
     * @param businessName 业务对象名字
     * @return 业务对象
     */
    BusinessObject findByBusinessName(String businessName);
}
