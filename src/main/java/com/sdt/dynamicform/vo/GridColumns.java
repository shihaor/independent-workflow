package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * 栅栏布局不可用
 *
 * @date 2019/11/5
 * @author shihaoran
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GridColumns implements Serializable {

    private static final long serialVersionUID = 4921075380736980552L;

    /**
     * span  长度
     */
    private Integer span;

    /**
     * 文本类型
     */
    private List<FormFieldVO> list;

    public Integer getSpan() {
        return span;
    }

    public void setSpan(Integer span) {
        this.span = span;
    }

    public List<FormFieldVO> getList() {
        return list;
    }

    public void setList(List<FormFieldVO> list) {
        this.list = list;
    }
}
