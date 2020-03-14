package com.sdt.dynamicform.service;

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
     * 保存或者更新表单
     *
     * @param containAllVO 表单数据
     */
    void saveOrUpdateForm(ContainAllVO containAllVO) throws Exception;

    /**
     * 查询表单集合
     *
     * @return 全部的表单
     */
    List<DynamicForm> obtainListForms();

    /**
     * 查询单条表单
     *
     * @param id 表单id
     * @return 表单
     */
    DynamicForm viewForm(Integer id);

    /**
     * 删除单条表单
     * @param id 表单id
     */
    void deleteForm(Integer id);
}
