package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 前端校验规则
 *
 * @date 2019/11/6
 * @author shihaoran
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rules implements Serializable {

    private static final long serialVersionUID = -7654622299856148214L;

    /**
     * 错误类型
     */
    private String type;

    /**
     * 错误消息
     */
    private String message;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
