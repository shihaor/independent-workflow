package com.sdt.common.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 适用于key-value型的VO
 *
 * @author shihaoran
 * @date 2020/3/16
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonVO {

    private String label;

    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
