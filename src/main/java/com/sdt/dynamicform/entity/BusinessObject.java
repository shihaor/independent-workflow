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

/**
 * 业务对象
 *
 * @date 2019/11/11
 * @author shihaoran
 */
@Entity
@Table(name = "e_business_obj")
@Scope("prototype")
@ApiModel(value = "业务对象名称")
@JsonIgnoreProperties(value = {"uuid", "deleted", "deleteTime"})
public class BusinessObject extends AbstractBaseBean {

    private static final long serialVersionUID = -7647495599959129185L;

    @ApiModelProperty(name = "流程定义id")
    @Column(name = "process_def_id", length = 64)
    private String processDefId;

    @ApiModelProperty(name = "业务对象名称")
    @Column(name = "business_name", length = 50, unique = true)
    private String businessName;

    @Lob
    @ApiModelProperty(name = "业务对象配置的绝对路径")
    @Column(name = "business_path", columnDefinition = "text")
    private String businessPath;

    @Column(name = "form_id", length = 36)
    @ApiModelProperty(name = "表单id对应表单表中的unique_biaoshi")
    private String formId;

    @Column(name = "type", length = 20)
    @ApiModelProperty(name = "业务对象的类型")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public void setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessPath() {
        return businessPath;
    }

    public void setBusinessPath(String businessPath) {
        this.businessPath = businessPath;
    }
}
