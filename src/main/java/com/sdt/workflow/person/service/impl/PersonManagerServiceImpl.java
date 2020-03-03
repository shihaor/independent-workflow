package com.sdt.workflow.person.service.impl;

import com.sdt.workflow.annotation.LoginComponent;
import com.sdt.workflow.person.service.PersonManagerService;
import com.sdt.workflow.person.vo.Person;
import org.activiti.engine.ActivitiException;
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
    public void checkPassword(Person person, HttpServletRequest request) {

        boolean password = identityService.checkPassword(person.getId(), person.getPassword());
        if (!password) {
            throw new ActivitiException("登录信息错误");
        }
        User user = identityService.createUserQuery().userId(person.getId()).singleResult();
        person.setName(user.getFirstName());
        request.getSession().setAttribute("person", person);
    }
}
