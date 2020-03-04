package com.sdt.dynamicform.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sdt.eims.base.bean.AbstractBaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.context.annotation.Scope;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 表单实体
 *
 * @date 2019/11/4
 * @author shihaoran
 */
@Entity
@Table(name = "e_form")
@Scope("prototype")
@ApiModel(value = "表单实体")
@JsonIgnoreProperties(value = {"uuid", "deleted", "deleteTime", "unique_biaoshi"})
public class DynamicForm extends AbstractBaseBean implements Serializable {

    private static final long serialVersionUID = 6904659384549779360L;

    @ApiModelProperty(name = "表单的名字")
    @Column(name = "form_name", length = 60)
    private String formName;

    @ApiModelProperty(name = "业务对象的id")
    @Column(name = "business_id", length = 64)
    private String businessId;

    @ApiModelProperty(name = "业务对象的名称")
    @Column(name = "business_name", length = 64)
    private String businessName;

    @ApiModelProperty(name = "版本", dataType = "Long")
    @Column(name = "form_version", length = 20)
    private Long formVersion;

    @Lob
    @ApiModelProperty(name = "表单的json数据")
    @Column(name = "form_data", columnDefinition = "text")
    private String formData;

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Long getFormVersion() {
        return formVersion;
    }

    public void setFormVersion(Long formVersion) {
        this.formVersion = formVersion;
    }

    public String getFormData() {
        return formData;
    }

    public void setFormData(String formData) {
        this.formData = formData;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

}
