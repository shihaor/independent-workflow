package com.sdt.workflow.manager.service;

import com.sdt.workflow.manager.GitLog;
import org.eclipse.jgit.api.errors.GitAPIException;

import java.io.IOException;
import java.util.List;

/**
 * 首页服务接口
 *
 * @author shihaoran
 * @date 2020/2/25
 */
public interface IndexManagerService {

    /**
     * 获取账户数目
     *
     * @return 账户数目
     */
    int getAccountNumber();

    /**
     * 获取部门数目
     *
     * @return 部门数目
     */
    int getOrgNumber();

    /**
     * 获取模型的数量
     *
     * @return 模型的数量
     */
    int getModelNumber();

    /**
     * 获取已部署的数目
     *
     * @return 已部署的数目
     */
    int getDeploymentNumber();

    /**
     * 获取正在执行的流程的数目
     *
     * @return 正在执行的流程数目
     */
    int getRunningNumber();

    /**
     * 获取已完成的流程数目
     *
     * @return 已完成的流程数目
     */
    int getOverNumber();

    /**
     * 获取git提交信息
     *
     * @return git提交信息
     */
    List<GitLog> getCommitLog() throws IOException, GitAPIException;
}
