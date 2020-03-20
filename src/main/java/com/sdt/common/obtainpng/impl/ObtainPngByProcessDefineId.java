package com.sdt.common.obtainpng.impl;

import com.sdt.common.obtainpng.IObtainPng;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 通过流程定义id获取图片字节流
 *
 * @author shihaoran
 * @date 2020/3/12
 */
@Scope("prototype")
@Service(value = "ObtainPngByProcessDefineId")
public class ObtainPngByProcessDefineId implements IObtainPng {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public byte[] obtainPngByte(String condition) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(condition).singleResult();
        Model model = repositoryService.createModelQuery().deploymentId(processDefinition.getDeploymentId()).singleResult();
        byte[] sources = repositoryService.getModelEditorSourceExtra(model.getId());
        return sources;
    }
}
