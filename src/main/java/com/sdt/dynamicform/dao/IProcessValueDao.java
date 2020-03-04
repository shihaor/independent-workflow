package com.sdt.dynamicform.dao;

import com.sdt.dynamicform.entity.FormValueLinkProcess;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 查询{@link FormValueLinkProcess}实体的dao
 *
 * @date 2019/11/13
 * @author shihaoran
 */
public interface IProcessValueDao extends JpaRepository<FormValueLinkProcess, Integer> {

    /**
     * 通过流程实例id获取携带的设备id，这个方法只会获取第一条数据，就是启动节点的数据
     * 有多少节点就会存在多少条重复的，区别在于taskID和只有第一个记录有设备id
     *
     * @param processInstanceId 流程实例id
     * @return 表单详细
     * @throws Exception 查找异常
     */
    FormValueLinkProcess findByProcessInstanceId(String processInstanceId) throws Exception;
}
