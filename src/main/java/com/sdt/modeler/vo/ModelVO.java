package com.sdt.modeler.vo;

/**
 * 模型的需要的属性
 *
 * @author shihaoran
 * @date 2020/2/1
 */
public class ModelVO {

    /** 名字 */
    private String name;

    /** key */
    private String key;

    /** 模型ID */
    private String modelId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
