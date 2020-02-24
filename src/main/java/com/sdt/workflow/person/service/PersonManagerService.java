package com.sdt.workflow.person.service;

import com.sdt.workflow.person.vo.Person;

import javax.servlet.http.HttpServletRequest;

/**
 * 人员信息管理服务接口
 *
 * @author shihaoran
 * @date 2020/2/23
 */
public interface PersonManagerService {

    /**
     * 登录检查
     *
     * @param person 待登录人信息
     * @param request 上下文请求信息
     */
    void checkPassword(Person person, HttpServletRequest request);

}
