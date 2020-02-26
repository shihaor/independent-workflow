package com.sdt.workflow.core.service.impl;

import com.sdt.workflow.core.service.DeleteActivitiService;
import org.activiti.engine.RuntimeService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author shihaoran
 * @date 2020/2/26
 */
@Service
@Scope("prototype")
@Transactional(rollbackOn = Exception.class)
public class DeleteActivitiServiceImpl implements DeleteActivitiService {

    @Resource
    private RuntimeService runtimeService;


    @Override
    public void deleteNoReason(String processInstanceId) {
        runtimeService.deleteProcessInstance(processInstanceId, "删除");
    }
}
