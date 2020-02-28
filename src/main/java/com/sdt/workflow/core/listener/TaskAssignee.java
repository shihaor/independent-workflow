package com.sdt.workflow.core.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 定死某个人接收
 *
 * @author shihaoran
 * @date 2020/2/28
 */
public class TaskAssignee implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("2");
    }
}
