package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 表单的值
 *
 * @author shihaoran
 * @date 2020/3/16
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormData {

    /**
     * 表单中控件的唯一标识
     */
    private String prop;

    /**
     * 控件的值
     */
    private String value;

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
