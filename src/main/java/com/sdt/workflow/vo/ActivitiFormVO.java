package com.sdt.workflow.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdt.dynamicform.vo.ContainAllVO;
import com.sdt.dynamicform.vo.FormData;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 流程和表单参数前端传递的vo
 *
 * @author shihaoran
 * @date 2020/3/16
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivitiFormVO {

    @ApiModelProperty(value = "表单的样式")
    private ContainAllVO containAllVO;

    @ApiModelProperty(value = "表单的值")
    private List<FormData> formData;

    @ApiModelProperty(value = "流程定义id")
    private String processDefineId;

    @ApiModelProperty(value = "节点id")
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public ContainAllVO getContainAllVO() {
        return containAllVO;
    }

    public void setContainAllVO(ContainAllVO containAllVO) {
        this.containAllVO = containAllVO;
    }

    public List<FormData> getFormData() {
        return formData;
    }

    public void setFormData(List<FormData> formData) {
        this.formData = formData;
    }

    public String getProcessDefineId() {
        return processDefineId;
    }

    public void setProcessDefineId(String processDefineId) {
        this.processDefineId = processDefineId;
    }
}
