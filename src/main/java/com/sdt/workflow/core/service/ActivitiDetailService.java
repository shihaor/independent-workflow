package com.sdt.workflow.core.service;

import java.io.InputStream;

/**
 * 工作流获取详细的接口
 *
 * @author shihaoran
 * @date 2020/3/4
 */
public interface ActivitiDetailService {

    /**
     * 获取当前流程的PNG图片
     *
     * @param processInstanceId 流程实例Id
     * @return 图片的字节流
     * @throws Exception 获取当前节点图片异常
     */
    InputStream obtainProcessNowNodePng(String processInstanceId);
}
