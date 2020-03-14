package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 图片水印设置
 *
 * @author shihaoran
 * @date 2020/3/14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CanvasOption {

    /**
     * 文字大小
     */
    private Integer fontSize;

    /**
     * 文字的透明度
     */
    private Integer opacity;

    /**
     * 文字距离图片底部的距离
     */
    private Integer bottom;

    /**
     * 文字距离图片右边的距离
     */
    private Integer right;

    /**
     * 压缩图片比率
     */
    private Integer ratio;

    /**
     * 字体
     */
    private String text;

    /**
     * 字体类型
     */
    private String fontFamily;

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getOpacity() {
        return opacity;
    }

    public void setOpacity(Integer opacity) {
        this.opacity = opacity;
    }

    public Integer getBottom() {
        return bottom;
    }

    public void setBottom(Integer bottom) {
        this.bottom = bottom;
    }

    public Integer getRight() {
        return right;
    }

    public void setRight(Integer right) {
        this.right = right;
    }

    public Integer getRatio() {
        return ratio;
    }

    public void setRatio(Integer ratio) {
        this.ratio = ratio;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public void setFontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
    }
}
