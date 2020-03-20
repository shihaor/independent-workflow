package com.sdt.workflow.core.service.impl;

import com.sdt.common.exception.GlobalException;
import com.sdt.common.result.CodeMsg;
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
        try {
            runtimeService.deleteProcessInstance(processInstanceId, "删除");
        } catch (Exception e) {
            throw new GlobalException(CodeMsg.DELETE_PROCESS_FAIL);
        }
    }

    @Override
    public void deleteHasReason(String processInstanceId, String reason) {
        try {
            runtimeService.deleteProcessInstance(processInstanceId, reason);
        } catch (Exception e) {
            throw new GlobalException(CodeMsg.DELETE_PROCESS_FAIL);
        }
    }
}
