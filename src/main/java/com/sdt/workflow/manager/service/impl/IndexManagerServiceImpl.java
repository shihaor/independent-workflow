package com.sdt.workflow.manager.service.impl;

import com.sdt.workflow.manager.GitLog;
import com.sdt.workflow.manager.service.IndexManagerService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 首页服务实现类
 *
 * @author shihaoran
 * @date 2020/2/25
 */
@Service
@Scope("prototype")
public class IndexManagerServiceImpl implements IndexManagerService {

    @Resource
    private IdentityService identityService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private HistoryService historyService;

    @Override
    public int getAccountNumber() {
        return identityService.createUserQuery().list().size();
    }

    @Override
    public int getOrgNumber() {
        return identityService.createGroupQuery().list().size();
    }

    @Override
    public int getModelNumber() {
        return repositoryService.createModelQuery().list().size();
    }

    @Override
    public int getDeploymentNumber() {
        return repositoryService.createDeploymentQuery().list().size();
    }

    @Override
    public int getRunningNumber() {
        return runtimeService.createProcessInstanceQuery().list().size();
    }

    @Override
    public int getOverNumber() {
        return historyService.createHistoricProcessInstanceQuery().finished().list().size();
    }

    @Override
    public List<GitLog> getCommitLog() throws IOException, GitAPIException {

        Git git = Git.open(new File("F:/workpeace/independent-workflow/"));
        Iterable<RevCommit> call = git.log().call();
        Iterator<RevCommit> iterator = call.iterator();
        GitLog gitLog = null;
        ArrayList<GitLog> list = new ArrayList<>();
        while (iterator.hasNext()) {
            gitLog = new GitLog();
            RevCommit commit = iterator.next();
            Date date = commit.getAuthorIdent().getWhen();
            String fullMessage = commit.getFullMessage();
            gitLog.setData(date);
            gitLog.setMsg(fullMessage);
            list.add(gitLog);
        }
        return list;
    }
}
