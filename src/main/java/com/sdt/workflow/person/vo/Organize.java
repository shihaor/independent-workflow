package com.sdt.workflow.person.vo;

import javax.validation.constraints.NotNull;

/**
 * 机构信息
 *
 * @author shihaoran
 * @date 2020/2/22
 */
public class Organize {

    /**
     * 机构id
     */
    @NotNull(message = "机构id不能为空")
    private String orgId;

    /**
     * 机构name
     */
    @NotNull(message = "机构名字不能为空")
    private String name;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
