package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 规则
 *
 * @author shihaoran
 * @date 2020/3/14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rule {

    private Boolean required;

    private String message;

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
