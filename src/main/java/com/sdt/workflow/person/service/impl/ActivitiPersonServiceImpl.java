package com.sdt.workflow.person.service.impl;

import com.sdt.workflow.person.service.ActivitiPersonService;
import com.sdt.workflow.person.vo.Person;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 人员管理实现类
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Service
@Scope("prototype")
public class ActivitiPersonServiceImpl implements ActivitiPersonService {

    @Resource
    private IdentityService identityService;

    @Override
    public void addOrUpdate(Person person) {

        if (null == person) {
            throw new ActivitiException("人员信息为空，添加错误");
        }
        User user = identityService.createUserQuery().userId(person.getId()).singleResult();
        if (null == user) {
            user = identityService.newUser(person.getId());
        }
        if (StringUtils.isNotEmpty(person.getName())) {
            user.setFirstName(person.getName());
        }
        if (StringUtils.isNotEmpty(person.getLastName())) {
            user.setLastName(person.getLastName());
        }
        identityService.saveUser(user);
        if (StringUtils.isNotEmpty(person.getOrgId())) {
            identityService.createMembership(person.getId(), person.getOrgId());
        }
    }
}
