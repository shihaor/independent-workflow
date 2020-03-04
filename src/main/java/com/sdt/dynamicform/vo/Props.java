package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 属性
 *
 * @date 2019/11/5
 * @author shihaoran
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Props implements Serializable {

    private static final long serialVersionUID = 1564948225199067471L;

    /**
     * 值
     */
    private String value;

    /**
     * 标签
     */
    private String label;

    /**
     * 级联关系
     */
    private String children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }
}

