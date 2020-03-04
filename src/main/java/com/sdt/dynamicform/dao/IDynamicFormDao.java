package com.sdt.dynamicform.dao;

import com.sdt.dynamicform.entity.DynamicForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 动态表单的dao
 *
 * @author shihaoran
 * @date 2019/11/5
 */
public interface IDynamicFormDao extends JpaRepository<DynamicForm, Integer> {

    /**
     * 获取全部的表单
     *
     * @return 所有表单的集合
     * @throws Exception 查找异常
     */
    @Query(value = "select a.id, a.createTime, a.updateTime, a.formName, a.formVersion, a.businessName from DynamicForm a where a.formVersion = (select max(o.formVersion) from DynamicForm o where o.formName = a.formName)  ORDER BY a.createTime desc ")
    List<DynamicForm> findAllForm();

    /**
     * 获取表单总数
     *
     * @return 表单总数
     * @throws Exception 查找异常
     */
    @Query(value = "select DISTINCT(o.formName) from DynamicForm o ")
    int obtainFormsTotal() throws Exception;

    /**
     * 根据表单名字实行全部删除
     *
     * @param formName 表单名字
     * @throws Exception 更新异常
     */
    void deleteByFormName(String formName) throws Exception;

    /**
     * 通过名字查找表单，默认查找版本最高的
     *
     * @param formName 表单名字
     * @return 表单实体
     * @throws Exception 查找异常
     */
    @Query(value = "select a from DynamicForm a where a.formName = ?1 and a.formVersion = (select max(o.formVersion) from DynamicForm o where o.formName = a.formName)")
    DynamicForm findMaxVersionFormByName(@Param("formName") String formName) throws Exception;

    /**
     * 通过名字查找表单，查找全部版本的
     *
     * @param businessName 业务对象名字
     * @return 表单实体
     * @throws Exception 查找异常
     */
    List<DynamicForm> findAllByBusinessName(String businessName) throws Exception;

    /**
     * 通过流程定义id获取表单id
     *
     * @param processDefineId 流程定义id
     * @return 表单id
     */
    @Query(value = "select o.id from DynamicForm o where o.businessId = :processDefineId  ORDER BY o.formVersion desc")
    Integer getFormIdByProcessDefineId(@Param("processDefineId") String processDefineId);

    /**
     * 加锁查询
     *
     * @param id        表单的主键
     * @return 表单
     * @throws Exception 查询异常
     */
    DynamicForm findById(Integer id) throws Exception;

    /**
     * 通过表单名字和版本查询Form
     *
     * @param formName 表单名字
     * @param version  版本
     * @return {@link DynamicForm}DynamicForm
     * @throws Exception 查询 异常
     */
    DynamicForm findByFormNameAndFormVersion(String formName, long version) throws Exception;

    /**
     * 根据流程定义Id删除与之关联的全部表单，逻辑删除
     *
     * @param businessId 流程定义Id
     */
    void deleteByBusinessId(String businessId);

    /**
     * 寻找版本最低的表单
     *
     * @param formName 表单名字
     * @return 表单
     * @throws Exception 查询异常
     */
    @Query(value = "select o from DynamicForm o where o.formName = :formName order by formVersion asc")
    List<DynamicForm> findMinVersionByFormName(@Param("formName") String formName) throws Exception;

}
