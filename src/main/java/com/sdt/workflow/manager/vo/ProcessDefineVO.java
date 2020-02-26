package com.sdt.workflow.manager.vo;

/**
 * 流程定义VO
 *
 * @author shihaoran
 * @date 2020/2/26
 */
public class ProcessDefineVO {

    private String id;

    private String name;

    private int version;

    private String description;

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
