package com.sdt.workflow.manager.service.impl;

import com.sdt.common.exception.GlobalException;
import com.sdt.common.result.CodeMsg;
import com.sdt.workflow.manager.service.DeploymentOperatorService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 部署资源操作服务实现类
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Service
@Scope("prototype")
public class DeploymentOperatorServiceImpl implements DeploymentOperatorService {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public void suspendDeployment(String id) {

        try {
            repositoryService.suspendProcessDefinitionById(id);
        } catch (Exception e) {
            throw new GlobalException(CodeMsg.SUSPEND_PROCESS_FAIL);
        }
    }

    @Override
    public void activeDeployment(String id) {

        try {
            repositoryService.activateProcessDefinitionById(id);
        } catch (Exception e) {
            throw new GlobalException(CodeMsg.ACTIVE_PROCESS_FAIL);
        }
    }

    @Override
    public void deleteDeployment(String id) {
        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
            // 连带历史记录都删除
            repositoryService.deleteDeployment(processDefinition.getDeploymentId(), true);
        } catch (Exception e) {
            throw new GlobalException(CodeMsg.DELETE_DEPLOYMENT_FAIL);
        }
    }
}
