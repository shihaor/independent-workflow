package com.sdt.workflow.core.service.impl;

import org.activiti.engine.IdentityService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 节点完成服务实现类
 *
 * @author shihaoran
 * @date 2020/2/24
 */
@Service
@Scope("prototype")
public class CompleteActivitiServiceImpl {

    @Resource
    private IdentityService identityService;
}
