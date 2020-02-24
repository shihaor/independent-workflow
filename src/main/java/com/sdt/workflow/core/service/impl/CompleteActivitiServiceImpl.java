package com.sdt.workflow.core.service.impl;

import com.sdt.workflow.core.service.CompleteActivitiService;
import com.sdt.workflow.utils.ActivitiUtil;
import org.activiti.engine.FormService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * 节点完成服务实现类
 *
 * @author shihaoran
 * @date 2020/2/24
 */
@Service
@Scope("prototype")
public class CompleteActivitiServiceImpl implements CompleteActivitiService {

    @Resource
    private IdentityService identityService;

    @Resource
    private TaskService taskService;

    @Resource
    private ActivitiUtil activitiUtil;

    @Resource
    private FormService formService;

    @Override
    public void noForm(String[] context, String taskId) {

        HashMap<String, Object> map = new HashMap<>(16);
        TaskFormData taskFormData = formService.getTaskFormData(taskId);
        List<FormProperty> propertyList = taskFormData.getFormProperties();
        for (int i = 0; i < propertyList.size(); i++) {
            map.put(propertyList.get(i).getId(), context[i]);
        }
        taskService.complete(taskId, map);
    }
}
