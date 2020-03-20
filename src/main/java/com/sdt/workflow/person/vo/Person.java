package com.sdt.workflow.person.vo;

/**
 * 前端传来的人员参数
 *
 * @author shihaoran
 * @date 2020/2/22
 */
public class Person {

    /**
     * 人员的id
     */
    private String id;

    /**
     * 人员的姓名
     */
    private String name;

    /**
     * 系统的名字
     */
    private String sysName;

    /**
     * 机构的姓名
     */
    private String orgId;

    /**
     * 密码
     */
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
