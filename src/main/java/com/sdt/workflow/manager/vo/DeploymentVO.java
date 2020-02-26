package com.sdt.workflow.manager.vo;

import java.util.Date;

/**
 * 部署的VO
 *
 * @author shihaoran
 * @date 2020/2/26
 */
public class DeploymentVO {

    /**
     * 部署id
     */
    private String id;

    /**
     * 部署名字
     */
    private String name;

    /**
     * 部署时间
     */
    private Date deploymentTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeploymentTime() {
        return deploymentTime;
    }

    public void setDeploymentTime(Date deploymentTime) {
        this.deploymentTime = deploymentTime;
    }
}
