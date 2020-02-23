package com.sdt.workflow.person.service.impl;

import com.sdt.workflow.annotation.LoginComponent;
import com.sdt.workflow.person.service.PersonManagerService;
import com.sdt.workflow.person.vo.Person;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author shihaoran
 * @date 2020/2/23
 */
@Service
@LoginComponent
@Scope("prototype")
public class PersonManagerServiceImpl implements PersonManagerService {

    @Resource
    private IdentityService identityService;

    @Override
    public void checkPassword(Person person) {

        boolean password = identityService.checkPassword(person.getId(), person.getPassword());
        if (!password) {
            throw new ActivitiException("登录信息错误");
        }
    }
}
