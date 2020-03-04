package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * 控件的父类属性
 *
 * @date 2019/11/4
 * @author shihaoran
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FormFieldVO implements Serializable {

    private static final long serialVersionUID = 5811573156332809927L;

    /**
     * 类型
     */
    private String type;

    /**
     * 单行文本
     */
    private String name;

    /**
     * icon
     */
    private String icon;

    /**
     * 控件限制
     */
    private OptionVO options;

    /**
     * 控件的key
     */
    private String key;

    /**
     * 模型参数
     */
    private String model;

    /**
     * 前端校验规则
     */
    private List<Rules> rules;

    /**
     * 栅栏布局
     */
    private List<GridColumns> columns;

    /**
     * 远程
     */
    private Object remote;

    public List<GridColumns> getColumns() {
        return columns;
    }

    public void setColumns(List<GridColumns> columns) {
        this.columns = columns;
    }

    public Object getRemote() {
        return remote;
    }

    public void setRemote(Object remote) {
        this.remote = remote;
    }

    public List<Rules> getRules() {
        return rules;
    }

    public void setRules(List<Rules> rules) {
        this.rules = rules;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public OptionVO getOptions() {
        return options;
    }

    public void setOptions(OptionVO options) {
        this.options = options;
    }
}
