package com.sdt.dynamicform.service.impl;

import com.sdt.common.utils.CommonUtils;
import com.sdt.common.utils.JsonObjectMapper;
import com.sdt.dynamicform.dao.IBusinessObjectDao;
import com.sdt.dynamicform.dao.IDynamicFormDao;
import com.sdt.dynamicform.entity.BusinessObject;
import com.sdt.dynamicform.entity.DynamicForm;
import com.sdt.dynamicform.service.IDynamicFormsService;
import com.sdt.dynamicform.vo.ContainAllVO;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public void saveOrUpdateForm(ContainAllVO containAllVO) throws Exception {

        JsonObjectMapper mapper = new JsonObjectMapper();
        // 判断业务对象是否存在
        Integer id = containAllVO.getBusinessId();
        BusinessObject bus = businessObjectDao.findOne(id);
        if (null == bus) {
            throw new Exception("业务对象不存在，表单保存失败");
        }
        // 保存表单
        DynamicForm newForm = new DynamicForm();
        String formData = mapper.writeValueAsString(containAllVO);
        newForm.setFormData(formData);
        newForm.setBusinessId(containAllVO.getBusinessId());
        newForm.setFormName(containAllVO.getFormName());
        Integer formId = containAllVO.getId();
        System.out.println(containAllVO.getGroup().get(0).getColumn().get(0).getDicQuery().get());
        DynamicForm oldForm = dynamicFormDao.findOne(formId);
        if (null != oldForm) {
            BeanUtils.copyProperties(newForm, oldForm, CommonUtils.getNullPropertyNames(newForm));
        }
        dynamicFormDao.save(oldForm);
    }

    @Override
    public List<DynamicForm> obtainListForms() {
        Specification<DynamicForm> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.<String>get("deleted"), false));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
        return dynamicFormDao.findAll(specification);
    }

    @Override
    public DynamicForm viewForm(Integer id) {
        return dynamicFormDao.findOne(id);
    }

    @Override
    public void deleteForm(Integer id) {
        DynamicForm form = dynamicFormDao.findOne(id);
        form.setDeleted(true);
        dynamicFormDao.save(form);
    }
}
