package com.sdt.workflow.core.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.common.utils.ActivitiUtil;
import com.sdt.common.utils.JsonObjectMapper;
import com.sdt.dynamicform.dao.IProcessValueDao;
import com.sdt.dynamicform.entity.DynamicForm;
import com.sdt.dynamicform.entity.FormLinkBusiness;
import com.sdt.dynamicform.handler.ValidateHandler;
import com.sdt.workflow.core.service.StartActivitiService;
import com.sdt.workflow.person.vo.Person;
import com.sdt.workflow.vo.ActivitiFormVO;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

/**
 * 工作流启动实现类
 *
 * @author shihaoran
 * @date 2020/2/22
 */
@Service
@Scope("prototype")
@Transactional(rollbackOn = Exception.class)
public class StartActivitiServiceImpl implements StartActivitiService {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private IdentityService identityService;

    @Resource
    private ActivitiUtil activitiUtil;

    @Resource
    private ValidateHandler validateHandler;

    @Resource
    private IProcessValueDao processValueDao;

    @Value("${session.userId}")
    private String user;

    @Override
    public HashMap<String, Object> noForm(String processDefineId, HttpServletRequest request) {
        // 从session中获取人员
        Person person = (Person) request.getSession().getAttribute(user);
        HashMap<String, Object> map = new HashMap<>(2);
        Task task = this.startProcess(processDefineId, person);
        // 获取内置表单
        List<FormProperty> propertyList = activitiUtil.getFormProperties(task.getId());
        map.put("list", propertyList);
        map.put("taskId", task.getId());
        return map;
    }

    @Override
    public List<FormProperty> normalTask(String taskId) {
        return activitiUtil.getFormProperties(taskId);
    }

    @Override
    public void hasForm(ActivitiFormVO activitiFormVO, HttpServletRequest request) throws JsonProcessingException {

        JsonObjectMapper mapper = new JsonObjectMapper();
        // 从session中获取人员
        Person person = (Person) request.getSession().getAttribute(user);
        // 1、根据流程定义id查找业务对象
        String processDefineId = activitiFormVO.getProcessDefineId();
        DynamicForm form = validateHandler.checkFormAndBusiness(processDefineId);
        Task task = this.startProcess(processDefineId, person);
        String taskId = task.getId();
        // 2、将变量全部放入流程变量表
        activitiFormVO.getFormData().forEach(formData -> taskService.setVariable(taskId, formData.getProp(), formData.getValue()));
        // 3、完成第一个节点
        taskService.setOwner(taskId, user);
        taskService.claim(taskId, user);
        taskService.complete(taskId);
        // 4、将数据保存到表单关联流程表中
        FormLinkBusiness busValue = new FormLinkBusiness();
        busValue.setFormNewStyle(mapper.writeValueAsString(activitiFormVO.getContainAllVO()));
        busValue.setFormOldStyle(mapper.writeValueAsString(form.getFormStyle()));
        busValue.setFormData(mapper.writeValueAsString(activitiFormVO.getFormData()));
        busValue.setProcessInstanceId(task.getProcessInstanceId());
        processValueDao.save(busValue);
    }

    /**
     * 启动流程并返回第一个节点
     *
     * @param processDefineId 流程定义id
     * @param person          人员信息
     * @return 第一个节点
     */
    private Task startProcess(String processDefineId, Person person) {

        // 给Bpmn图中的activiti:initor赋值
        identityService.setAuthenticatedUserId(user);
        // 启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefineId);
        // 给流程设置启动人
        repositoryService.addCandidateStarterUser(processDefineId, user);
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId()).singleResult();
        taskService.claim(task.getId(), user);
        return task;
    }
}
