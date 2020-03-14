package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 控件的静态值
 *
 * @author shihaoran
 * @date 2020/3/14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DicData {

    /**
     * 值的标签
     */
    private String label;

    /**
     * 值
     */
    private Object value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
