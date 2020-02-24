package com.sdt.workflow.manager.service.impl;

import com.sdt.workflow.manager.service.DeploymentOperatorService;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
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

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(id).singleResult();
        try {
            repositoryService.suspendProcessDefinitionById(processDefinition.getId());
        } catch (Exception e) {
            throw new ActivitiException("重复挂起");
        }
    }

    @Override
    public void activeDeployment(String id) {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(id).singleResult();
        try {
            repositoryService.activateProcessDefinitionById(processDefinition.getId());
        } catch (Exception e) {
            throw new ActivitiException("重复激活");
        }
    }

    @Override
    public void deleteDeployment(String id) {

        try {
            // 连带历史记录都删除
            repositoryService.deleteDeployment(id, true);
        } catch (Exception e) {
            throw new ActivitiException("删除失败");
        }
    }

    @Override
    public byte[] getBpmn(String id) {

        Model model = repositoryService.createModelQuery().deploymentId(id).singleResult();
        byte[] sources = repositoryService.getModelEditorSourceExtra(model.getId());
        return sources;
    }
}
