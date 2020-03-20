package com.sdt.workflow.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.workflow.vo.ActivitiFormVO;
import org.activiti.engine.form.FormProperty;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * 工作流启动接口
 *
 * @author shihaoran
 * @date 2020/2/22
 */
public interface StartActivitiService {

    /**
     * 无表单启动
     *
     * @param processDefineId 流程定义id
     * @param request
     * @return 节点中的表单数据 和 节点id
     */
    HashMap<String, Object> noForm(String processDefineId, HttpServletRequest request);

    /**
     * 通过taskId获取当前节点的表单
     *
     * @param taskId
     * @return
     */
    List<FormProperty> normalTask(String taskId);

    /**
     * 流程携带表单启动
     *
     * @param activitiFormVO 流程启动需要的参数
     * @param request        上下文请求
     */
    void hasForm(ActivitiFormVO activitiFormVO, HttpServletRequest request) throws JsonProcessingException;
}
