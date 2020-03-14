package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.List;

/**
 * 表单的全部内容
 *
 * @date 2019/11/5
 * @author shihaoran
 */
@ApiModel(value = "解析表单json类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContainAllVO implements Serializable {

    private static final long serialVersionUID = -5217284035718038879L;

    /**
     * 表单id
     */
    private Integer id;

    /**
     * 表单控件类型集合
     */
    private List<Column> column;

    /**
     * 标签方向
     */
    private String labelPosition;

    /**
     * 标签后缀
     */
    private String labelSuffix;

    /**
     * 项与项之间的间隔
     */
    private Integer gutter;

    /**
     * 标签宽度
     */
    private String labelWidth;

    /**
     * 显示按钮
     */
    private Boolean menuBtn;

    /**
     * 显示提交按钮
     */
    private Boolean submitBtn;

    /**
     * 提交按钮的大小
     */
    private String submitSize;

    /**
     * 提交按钮的内容
     */
    private String submitText;

    /**
     * 显示清空按钮
     */
    private Boolean emptyBtn;

    /**
     * 清空按钮大小
     */
    private String emptySize;

    /**
     * 清空按钮内容
     */
    private String emptyText;

    /**
     * 菜单按钮位置
     */
    private String menuPosition;

    /**
     * 菜单按钮排版
     */
    private String menuPostion;

    /**
     * 分组
     */
    private List<Group> group;

    /**
     * 表单名字
     */
    private String formName;

    /**
     * 业务对象的id
     */
    private Integer BusinessId;

    public Integer getBusinessId() {
        return BusinessId;
    }

    public void setBusinessId(Integer businessId) {
        BusinessId = businessId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public List<Column> getColumn() {
        return column;
    }

    public void setColumn(List<Column> column) {
        this.column = column;
    }

    public String getLabelPosition() {
        return labelPosition;
    }

    public void setLabelPosition(String labelPosition) {
        this.labelPosition = labelPosition;
    }

    public String getLabelSuffix() {
        return labelSuffix;
    }

    public void setLabelSuffix(String labelSuffix) {
        this.labelSuffix = labelSuffix;
    }

    public Integer getGutter() {
        return gutter;
    }

    public void setGutter(Integer gutter) {
        this.gutter = gutter;
    }

    public String getLabelWidth() {
        return labelWidth;
    }

    public void setLabelWidth(String labelWidth) {
        this.labelWidth = labelWidth;
    }

    public Boolean getMenuBtn() {
        return menuBtn;
    }

    public void setMenuBtn(Boolean menuBtn) {
        this.menuBtn = menuBtn;
    }

    public Boolean getSubmitBtn() {
        return submitBtn;
    }

    public void setSubmitBtn(Boolean submitBtn) {
        this.submitBtn = submitBtn;
    }

    public String getSubmitSize() {
        return submitSize;
    }

    public void setSubmitSize(String submitSize) {
        this.submitSize = submitSize;
    }

    public String getSubmitText() {
        return submitText;
    }

    public void setSubmitText(String submitText) {
        this.submitText = submitText;
    }

    public Boolean getEmptyBtn() {
        return emptyBtn;
    }

    public void setEmptyBtn(Boolean emptyBtn) {
        this.emptyBtn = emptyBtn;
    }

    public String getEmptySize() {
        return emptySize;
    }

    public void setEmptySize(String emptySize) {
        this.emptySize = emptySize;
    }

    public String getEmptyText() {
        return emptyText;
    }

    public void setEmptyText(String emptyText) {
        this.emptyText = emptyText;
    }

    public String getMenuPosition() {
        return menuPosition;
    }

    public void setMenuPosition(String menuPosition) {
        this.menuPosition = menuPosition;
    }

    public String getMenuPostion() {
        return menuPostion;
    }

    public void setMenuPostion(String menuPostion) {
        this.menuPostion = menuPostion;
    }

    public List<Group> getGroup() {
        return group;
    }

    public void setGroup(List<Group> group) {
        this.group = group;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

}
