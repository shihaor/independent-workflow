package com.sdt.workflow.core.service;

import com.sdt.workflow.vo.ActivitiFormVO;
import org.activiti.engine.task.Task;

import javax.servlet.http.HttpServletRequest;

/**
 * 节点完成服务接口
 *
 * @author shihaoran
 * @date 2020/2/24
 */
public interface CompleteActivitiService {

    /**
     * 根据节点ID,将内置表单内容填入工作流引擎
     *
     * @param context 内置表单内容
     * @param taskId  节点id
     * @return 被完成的节点
     */
    Task noForm(String[] context, String taskId);

    /**
     * 根据表单携带的参数完成节点
     *
     * @param activitiFormVO 节点完成需要的参数
     * @param request        上下文请求参数
     * @return 待完成节点
     */
    Task hasForm(ActivitiFormVO activitiFormVO, HttpServletRequest request);
}
