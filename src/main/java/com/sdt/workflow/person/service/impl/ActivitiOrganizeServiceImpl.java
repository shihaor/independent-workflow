package com.sdt.workflow.person.service.impl;

import com.sdt.workflow.person.service.ActivitiOrganizeService;
import com.sdt.workflow.person.vo.Organize;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 工作流机构管理服务实现类
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Service
@Scope("prototype")
public class ActivitiOrganizeServiceImpl implements ActivitiOrganizeService {

    @Resource
    private IdentityService identityService;

    @Override
    public void addOrUpdate(Organize organize) {

        if (null == organize) {
            throw new ActivitiException("待添加机构信息为空");
        }
        Group group = identityService.createGroupQuery().groupId(organize.getOrgId()).singleResult();
        if (null == group) {
            group = identityService.newGroup(organize.getOrgId());
        }
        if (StringUtils.isNotEmpty(organize.getName())) {
            group.setName(organize.getName());
        }
        identityService.saveGroup(group);
    }
}
