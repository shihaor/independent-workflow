package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析JSON中的位置固定，字段不固定的key-value对
 *
 * @date 2019/11/6
 * @author shihaoran
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonMap implements Serializable {

    private static final long serialVersionUID = -9080060335120671436L;

    /**
     * 它没有实际的意义，就是为了解析存在的一个集合，可以把它理解为一个参数
     */
    private Map<String, Object> pro = new HashMap<>();

    @JsonAnySetter
    public void set(String fieldName, Object value) {
        pro.put(fieldName, value);
    }

    @JsonAnyGetter
    public Map<String, Object> get() {
        return this.pro;
    }
}
