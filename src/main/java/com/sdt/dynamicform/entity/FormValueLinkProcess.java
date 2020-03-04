package com.sdt.dynamicform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdt.eims.base.bean.AbstractBaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 不使用activti的数据表，需要自己新建一个
 *
 * @date 2019/11/13
 * @author shihaoran
 */
@Entity
@Table(name = "e_form_value")
@ApiModel(value = "流程每次数据不一样，需要以流程实例id为标识")
@JsonIgnoreProperties(value = {"uuid", "deleted", "deleteTime"})
public class FormValueLinkProcess extends AbstractBaseBean {

    private static final long serialVersionUID = 7421741401832804163L;

    @Column(name = "form_id", length = 50)
    @ApiModelProperty(value = "表单的id用来做标识")
    private Integer formId;

    @Lob
    @Column(name = "dev_id", columnDefinition = "text")
    @ApiModelProperty(value = "设备id")
    private String devIds;

    @Column(name = "pro_inst_id", length = 64)
    @ApiModelProperty(value = "流程的id用来做标识")
    private String processInstanceId;

    @Column(name = "task_id", length = 64)
    @ApiModelProperty(value = "流程的节点id用来做标识")
    private String taskId;

    @Lob
    @Column(name = "form_data", columnDefinition = "text")
    @ApiModelProperty(value = "表单的值")
    private String formData;

    @Lob
    @Column(name = "form_style", columnDefinition = "text")
    @ApiModelProperty(value = "控件的样式")
    private String formStyle;

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getDevIds() {
        return devIds;
    }

    public void setDevIds(String devIds) {
        this.devIds = devIds;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public String getFormStyle() {
        return formStyle;
    }

    public void setFormStyle(String formStyle) {
        this.formStyle = formStyle;
    }
}
