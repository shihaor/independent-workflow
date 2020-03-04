package com.sdt.dynamicform.vo;

/**
 * 前端resolve的模板
 *
 * @author shihaoran
 * @date 2019/12/27
 */
public class ResolveVO {

    private String label;

    private String value;

    public ResolveVO(String label, String value) {
        this.label = label;
        this.value = value;
    }

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
