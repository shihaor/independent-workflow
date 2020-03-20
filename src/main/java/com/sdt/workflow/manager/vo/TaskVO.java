package com.sdt.workflow.manager.vo;

/**
 * 任务VO
 *
 * @author shihaoran
 * @date 2020/2/26
 */
public class TaskVO {

    private String id;

    private String name;

    private String description;

    private String startTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
