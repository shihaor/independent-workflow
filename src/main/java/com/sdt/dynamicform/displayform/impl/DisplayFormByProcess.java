package com.sdt.dynamicform.displayform.impl;

import com.sdt.dynamicform.entity.DynamicForm;
import com.sdt.dynamicform.handler.ValidateHandler;
import com.sdt.dynamicform.vo.ContainAllVO;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 根据流程定义展示表单
 *
 * @author shihaoran
 * @date 2020/3/16
 */
@Scope("prototype")
@Service("displayFormByProcess")
public class DisplayFormByProcess extends AbstractDisplayForm {

    @Resource
    private ValidateHandler validateHandler;

    @Override
    public ContainAllVO displayForm(String processDefineId) throws IOException {

        DynamicForm form = validateHandler.checkFormAndBusiness(processDefineId);
        // 查询业务对象
        String formStyle = form.getFormStyle();
        ContainAllVO containAllVO = mapper.readValue(formStyle, ContainAllVO.class);
        // 处理节点显示 开始节点 id = apply
        ContainAllVO result = super.changeStyle(containAllVO, "apply");
        return result;
    }
}
