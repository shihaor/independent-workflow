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
     * 人员的别名
     */
    private String lastName;

    /**
     * 机构的姓名
     */
    private String orgId;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
