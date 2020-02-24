package com.sdt.workflow.core.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 动态指定下个节点接收人
 *
 * @author shihaoran
 * @date 2020/2/24
 */
public class DynamicAssignee implements TaskListener {


    @Override
    public void notify(DelegateTask delegateTask) {

        String userId = delegateTask.getVariable("received").toString();
        delegateTask.addCandidateUser(userId);
    }
}
