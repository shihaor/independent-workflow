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
 * @author shihaoran
 * @date 2019/11/13
 */
@Entity
@Table(name = "form_link_bus")
@ApiModel(value = "流程每次数据不一样，需要以流程实例id为标识")
@JsonIgnoreProperties(value = {"uuid", "deleted", "deleteTime"})
public class FormLinkBusiness extends AbstractBaseBean {

    private static final long serialVersionUID = 7421741401832804163L;

    @Column(name = "pro_inst_id", length = 64)
    @ApiModelProperty(value = "流程的id用来做标识")
    private String processInstanceId;

    @Lob
    @Column(name = "form_data", columnDefinition = "text")
    @ApiModelProperty(value = "表单的值")
    private String formData;

    @Lob
    @Column(name = "form_old_style", columnDefinition = "text")
    @ApiModelProperty(value = "控件的旧样式")
    private String formOldStyle;

    @Lob
    @Column(name = "form_new_style", columnDefinition = "text")
    @ApiModelProperty(value = "控件的随流程运转改变的样式")
    private String formNewStyle;

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public String getFormOldStyle() {
        return formOldStyle;
    }

    public void setFormOldStyle(String formOldStyle) {
        this.formOldStyle = formOldStyle;
    }

    public String getFormNewStyle() {
        return formNewStyle;
    }

    public void setFormNewStyle(String formNewStyle) {
        this.formNewStyle = formNewStyle;
    }
}
