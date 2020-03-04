package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 表单的布局设置
 *
 * @date 2019/11/5
 * @author shihaoran
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormConfig implements Serializable {

    private static final long serialVersionUID = -1234859320194764091L;

    /**
     * 标签宽度
     */
    private int labelWidth;

    /**
     * 标签位置
     */
    private String labelPosition;

    /**
     * 大小
     */
    private String size;

    public int getLabelWidth() {
        return labelWidth;
    }

    public void setLabelWidth(int labelWidth) {
        this.labelWidth = labelWidth;
    }

    public void setLabelPosition(String labelPosition) {
        this.labelPosition = labelPosition;
    }

    public String getLabelPosition() {
        return labelPosition;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
