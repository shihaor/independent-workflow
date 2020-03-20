package com.sdt.workflow.person.service.impl;

import com.sdt.common.annotation.LoginComponent;
import com.sdt.common.exception.GlobalException;
import com.sdt.common.result.CodeMsg;
import com.sdt.workflow.person.service.PersonManagerService;
import com.sdt.workflow.person.vo.Person;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
    public Person checkPassword(Person person, HttpServletRequest request) {

        boolean password = identityService.checkPassword(person.getId(), person.getPassword());
        if (!password) {
            throw new GlobalException(CodeMsg.PERSON_INFO_ERROR);
        }
        User user = identityService.createUserQuery().userId(person.getId()).singleResult();
        person.setName(user.getFirstName());
        request.getSession().setAttribute("person", person);
        request.getSession().setAttribute("userId", person.getId());return person;
    }
}
