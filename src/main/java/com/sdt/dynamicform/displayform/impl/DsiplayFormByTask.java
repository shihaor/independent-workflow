package com.sdt.dynamicform.displayform.impl;

import com.sdt.dynamicform.dao.IProcessValueDao;
import com.sdt.dynamicform.entity.FormLinkBusiness;
import com.sdt.dynamicform.vo.ContainAllVO;
import org.activiti.engine.TaskService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 展示节点表单
 *
 * @author shihaoran
 * @date 2020/3/16
 */
@Scope("prototype")
@Service("displayFormByTask")
public class DsiplayFormByTask extends AbstractDisplayForm {

    @Resource
    private TaskService taskService;

    @Resource
    private IProcessValueDao processValueDao;

    @Override
    public FormLinkBusiness displayForm(String taskId) throws IOException {

        // 1、节点id查找流程实例Id
        String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();
        // 2、通过流程实例id查找表单样式和内容
        FormLinkBusiness form = processValueDao.findByProcessInstanceId(processInstanceId);
        // 3、仅修改表单的样式 拿出初次运转表单的样式，进行修改后，放入新的样式中
        String formStyle = form.getFormOldStyle();
        ContainAllVO containAllVO = mapper.readValue(formStyle, ContainAllVO.class);
        ContainAllVO style = changeStyle(containAllVO, taskId);
        form.setFormNewStyle(mapper.writeValueAsString(style));
        // 返回表单的样式和数据
        return form;
    }
}
