package com.sdt.dynamicform.handler;

import com.sdt.common.exception.GlobalException;
import com.sdt.common.result.CodeMsg;
import com.sdt.dynamicform.dao.IBusinessObjectDao;
import com.sdt.dynamicform.dao.IDynamicFormDao;
import com.sdt.dynamicform.entity.BusinessObject;
import com.sdt.dynamicform.entity.DynamicForm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 对表单业务进行基础的校验
 *
 * @author shihaoran
 * @date 2020/3/16
 */
@Component
public class ValidateHandler {

    @Resource
    private IBusinessObjectDao businessObjectDao;

    @Resource
    private IDynamicFormDao dynamicFormDao;

    public DynamicForm checkFormAndBusiness(String processDefineId) {
        BusinessObject obj = businessObjectDao.findByProcessDefineIdAndDeletedIsFalse(processDefineId);
        if (null == obj) {
            throw new GlobalException(CodeMsg.BUSINESS_IS_EMPTY);
        }
        // 根据业务对象查找表单
        DynamicForm form = dynamicFormDao.findByBusinessId(obj.getId());
        if (null == form) {
            throw new GlobalException(CodeMsg.FORM_IS_EMPTY);
        }
        return form;
    }
}
