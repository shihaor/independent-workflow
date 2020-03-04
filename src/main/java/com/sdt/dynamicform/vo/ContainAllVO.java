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
     * 表单控件类型集合
     */
    private List<FormFieldVO> list;

    /**
     * 表单配置
     */
    private FormConfig config;

    /**
     * 表单名字
     */
    private String formName;

    /**
     * 表单id
     */
    private Integer id;

    /**
     * 业务对象名称
     */
    private String procName;

    /**
     * 流程实例id
     */
    private String processDefineId;

    public String getProcessDefineId() {
        return processDefineId;
    }

    public void setProcessDefineId(String processDefineId) {
        this.processDefineId = processDefineId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcName() {
        return procName;
    }

    public void setProcName(String procName) {
        this.procName = procName;
    }

    public void setList(List<FormFieldVO> list) {
        this.list = list;
    }

    public List<FormFieldVO> getList() {
        return list;
    }

    public void setConfig(FormConfig config) {
        this.config = config;
    }

    public FormConfig getConfig() {
        return config;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}
