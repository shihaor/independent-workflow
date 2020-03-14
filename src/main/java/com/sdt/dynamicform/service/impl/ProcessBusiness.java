package com.sdt.dynamicform.service.impl;

import com.sdt.common.utils.CommonUtils;
import com.sdt.dynamicform.entity.BusinessObject;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 流程业务对象操作
 *
 * @author shihaoran
 * @date 2020/3/14
 */
@Scope("prototype")
@Service("processBusiness")
public class ProcessBusiness extends BusinessObjServiceImpl {

    @Resource
    private RepositoryService repositoryService;

    @Override
    public void addOrUpdate(BusinessObject businessObject) throws Exception {

        // 通过业务对象的流程id 来确定流程是否存在
        String processDefineId = businessObject.getProcessDefineId();
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefineId).singleResult();
        if (null == definition) {
            throw new Exception("保存失败，没有查找到流程对象");
        }
        Integer id = businessObject.getId();
        BusinessObject oldObj = super.businessObjectDao.findOne(id);
        if (oldObj != null) {
            BeanUtils.copyProperties(businessObject, oldObj, CommonUtils.getNullPropertyNames(businessObject));
        }
        super.businessObjectDao.save(oldObj);
    }
}
