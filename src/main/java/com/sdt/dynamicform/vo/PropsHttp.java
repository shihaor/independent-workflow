package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 上传服务器返回的参数设置
 *
 * @author shihaoran
 * @date 2020/3/14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PropsHttp {

    /**
     * 上传成功返回结构体的图片地址
     */
    private String url;

    /**
     * 上传成功返回结构体的图片的姓名
     */
    private String name;

    /**
     * 返回结构体的层次
     */
    private String res;

    /**
     * 上传文件流时的名称
     */
    private String fileName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
