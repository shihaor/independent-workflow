package com.sdt.workflow.manager.service.impl;

import com.sdt.workflow.manager.service.ActivitiManagerService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 工作流管理服务实现类
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Service
@Scope("prototype")
public class ActivitiManagerServiceImpl implements ActivitiManagerService {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public List<Deployment> findAllDeployments() {

        List<Deployment> list = repositoryService.createDeploymentQuery().orderByDeploymenTime().desc().list();
        return list;
    }
}
