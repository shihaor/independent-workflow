package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 富文本编辑器
 *
 * @author shihaoran
 * @date 2020/3/14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Options {

    private String action;

    private String oss;

    private List props;

    private List ali;

    private List qinlu;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOss() {
        return oss;
    }

    public void setOss(String oss) {
        this.oss = oss;
    }

    public List getProps() {
        return props;
    }

    public void setProps(List props) {
        this.props = props;
    }

    public List getAli() {
        return ali;
    }

    public void setAli(List ali) {
        this.ali = ali;
    }

    public List getQinlu() {
        return qinlu;
    }

    public void setQinlu(List qinlu) {
        this.qinlu = qinlu;
    }
}
