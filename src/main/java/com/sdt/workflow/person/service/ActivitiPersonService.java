package com.sdt.workflow.person.service;

import com.sdt.workflow.person.vo.Person;

/**
 * 人员管理服务的接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
public interface ActivitiPersonService {

    /**
     * 添加人员
     *
     * @param person 待添加人员的信息
     */
    void addOrUpdate(Person person);
}
