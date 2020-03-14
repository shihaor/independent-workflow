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
 * 业务对象
 *
 * @date 2019/11/11
 * @author shihaoran
 */
@Entity
@Table(name = "business_obj")
@ApiModel(value = "业务对象名称")
@JsonIgnoreProperties(value = {"uuid", "deleted", "deleteTime"})
public class BusinessObject extends AbstractBaseBean {

    private static final long serialVersionUID = -7647495599959129185L;

    @ApiModelProperty(name = "流程定义id")
    @Column(name = "process_def_id", length = 64, unique = true)
    private String processDefineId;

    @ApiModelProperty(name = "其他业务定义")
    @Column(name = "other_business", length = 64)
    private String otherBusiness;

    @ApiModelProperty(name = "业务对象名称")
    @Column(name = "business_name", length = 50, unique = true)
    private String businessName;

    @Column(name = "type", length = 20)
    @ApiModelProperty(name = "业务对象的类型")
    private String type;

    @Lob
    @Column(name = "descript", columnDefinition = "text")
    private String descript;

    public String getOtherBusiness() {
        return otherBusiness;
    }

    public void setOtherBusiness(String otherBusiness) {
        this.otherBusiness = otherBusiness;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProcessDefineId() {
        return processDefineId;
    }

    public void setProcessDefineId(String processDefineId) {
        this.processDefineId = processDefineId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }


}
