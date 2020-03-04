package com.sdt.dynamicform.service.impl;

import com.sdt.common.bean.PagerBean;
import com.sdt.common.constant.DatePattern;
import com.sdt.common.utils.CommonUtils;
import com.sdt.common.utils.JsonObjectMapper;
import com.sdt.dynamicform.dao.IBusinessObjectDao;
import com.sdt.dynamicform.dao.IDynamicFormDao;
import com.sdt.dynamicform.entity.BusinessObject;
import com.sdt.dynamicform.entity.DynamicForm;
import com.sdt.dynamicform.service.IDynamicFormsService;
import com.sdt.dynamicform.vo.ContainAllVO;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * 表单的具体业务实现类
 *
 * @author shihaoran
 * @date 2019/11/5
 */
@Service
@Scope("prototype")
@Transactional(rollbackFor = Exception.class)
public class DynamicFormsServiceImpl implements IDynamicFormsService {

    @Resource
    private IDynamicFormDao dynamicFormDao;

    @Resource
    private IBusinessObjectDao businessObjectDao;

    @Resource
    private RepositoryService repositoryService;

    private final JsonObjectMapper mapper = new JsonObjectMapper();

    @Override
    public void saveOrUpdateForm(ContainAllVO template, String formName, Integer id, String procName) throws Exception {

        String unique = UUID.randomUUID().toString();
        // 表单概览信息
        saveForms(template, formName, id, procName, unique, template.getProcessDefineId());
    }

    @Override
    public List<DynamicForm> obtainListForms(PagerBean pagerBean) throws Exception {

        // 获取出所有表单
        List formList = dynamicFormDao.findAll();
        for (Object obj : formList) {
            Object[] form = (Object[]) obj;
            for (int i = 0; i < form.length; i++) {
                String formName = form[3].toString();
                if (i == 1) {
                    // 获取版本最低的表单的时间填充创建时间
                    DynamicForm oldForm = dynamicFormDao.findMinVersionByFormName(formName);
                    form[1] = oldForm.getCreateTime();
                }
            }
        }
        return formList;
    }

    @Override
    public DynamicForm viewForm(Integer id) throws Exception {

        return this.dynamicFormDao.findOne(id);
    }

    @Override
    public void deleteForm(Integer id) throws Exception {

        // 根据名字删除表单
        DynamicForm form = dynamicFormDao.getOne(id);
        this.dynamicFormDao.deleteByFormName(form.getFormName());
        BusinessObject businessObject = businessObjectDao.findByBusinessName(form.getBusinessName());
        if (null == businessObject) {
            throw new ActivitiException("表单删除错误，因为业务对象不存在");
        }
        businessObject.setFormId(null);
        businessObjectDao.save(businessObject);
    }

    @Override
    public DynamicForm findByName(String formName) throws Exception {

        return this.dynamicFormDao.findMaxVersionFormByName(formName);
    }

    /**
     * 保存表单概览
     *
     * @param template 前端传来的VO对象
     * @param formName 表单名字
     * @param id       表单id（必传）
     * @param procName 业务对象名字
     * @param unique   表单唯一标识
     * @throws Exception 表单保存异常
     */
    private void saveForms(ContainAllVO template, String formName, Integer id, String procName, String unique, String processDefineId) throws Exception {

        String jsonData = mapper.writeValueAsString(template);
        DynamicForm form = new DynamicForm();
        form.setFormName(formName);
        form.setBusinessId(processDefineId);
        form.setBusinessName(procName);
        form.setUuid(unique);

        // 通过业务名称获取业务对象
        List<BusinessObject> businessObjectList = businessObjectDao.findAllByBusinessName(procName);
        if (businessObjectList.isEmpty()) {
            throw new ActivitiException("表单保存错误，因为业务对象不存在");
        }
        BusinessObject businessObject = businessObjectList.get(0);
        if (null == id) {
            this.optimisticLock(formName, 1L);
            form.setFormVersion(1L);
            form.setUpdateTime(CommonUtils.getCurrentTime(DatePattern.DATE_ALL));
            if (StringUtils.isNotEmpty(businessObject.getFormId())) {
                throw new ActivitiException("表单保存错误，请刷新页面，重复提交了业务对象");
            }
        } else {
            DynamicForm oldForm = dynamicFormDao.getOne(id);
            //表单不提供低版本的修改，只提供对当前版本的修改
            if (null != oldForm && oldForm.getFormName().equals(formName)) {
                long formVersion = oldForm.getFormVersion() + 1L;
                this.optimisticLock(formName, formVersion);
                form.setFormVersion(formVersion);
                form.setUpdateTime(CommonUtils.getCurrentTime(DatePattern.DATE_ALL));
            }
        }
        form.setFormData(jsonData);

        // 保存表单的时候给业务对象的关联表单id赋值，由于获取不到id，所以获取表单生成的唯一标识
        businessObject.setFormId(unique);
        try {
            businessObjectDao.save(businessObject);
        } catch (Exception e) {
            throw new ActivitiException("业务对象关联异常");
        }

        try {
            dynamicFormDao.save(form);
        } catch (Exception e) {
            throw new ActivitiException("表单保存异常");
        }
    }

    /**
     * 添加一个乐观锁
     *
     * @param formName 表单名字
     * @param version  表单版本
     * @throws Exception 查询异常
     */
    private void optimisticLock(String formName, Long version) throws Exception {
        DynamicForm containForm = dynamicFormDao.findByFormNameAndFormVersion(formName, version);
        if (null != containForm) {
            throw new ActivitiException("有人抢先一步比您操作了表单！");
        }
    }

    @Override
    public void copyFormToOtherProc(ContainAllVO containAllVO, String processDefineId) throws Exception {

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefineId).singleResult();
        String name = processDefinition.getName();
        containAllVO.setProcName(name);
        containAllVO.setFormName(name + "表单");
        containAllVO.setProcessDefineId(processDefineId);
        this.saveOrUpdateForm(containAllVO, name, containAllVO.getId(), name);
    }
}
