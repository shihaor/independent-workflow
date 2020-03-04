package com.sdt.dynamicform.service;

import com.sdt.common.bean.PagerBean;
import com.sdt.dynamicform.entity.DynamicForm;
import com.sdt.dynamicform.vo.ContainAllVO;

import java.util.List;

/**
 * 动态表单的服务层接口
 *
 * @author shihaoran
 * @date 2019/11/5
 */
public interface IDynamicFormsService {

    /**
     * 保存或者更新表单实体
     *
     * @param list     json数据
     * @param formName 表单名称
     * @param id       表单id
     * @param procName 流程名字
     * @throws Exception 更新异常
     */
    void saveOrUpdateForm(ContainAllVO list, String formName, Integer id, String procName) throws Exception;

    /**
     * 查询全部的列表
     *
     * @param pagerBean 分页参数
     * @return 表单集合
     * @throws Exception 查找异常
     */
    List<DynamicForm> obtainListForms(PagerBean pagerBean) throws Exception;

    /**
     * 查看单条表单信息
     *
     * @param id 表单id
     * @return 表单实体
     * @throws Exception 查找异常
     */
    DynamicForm viewForm(Integer id) throws Exception;

    /**
     * 删除表单
     *
     * @param id 表单id
     * @return 成功与否
     * @throws Exception 更新异常
     */
    void deleteForm(Integer id) throws Exception;

    /**
     * 通过名字查找表单
     *
     * @param formName 表单名字
     * @return 表单实体
     * @throws Exception 查找异常
     */
    DynamicForm findByName(String formName) throws Exception;

    /**
     * 将表单复制给其他流程
     *
     * @param containAllVO    表单样式
     * @param processDefineId 将要接收表单的流程定义id
     * @throws Exception 保存表单异常
     */
    void copyFormToOtherProc(ContainAllVO containAllVO, String processDefineId) throws Exception;
}
