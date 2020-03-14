package com.sdt.dynamicform.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * 子表单
 *
 * @author shihaoran
 * @date 2020/3/14
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Children {

    private String align;

    private String headerAlign;

    private Boolean addBtn;

    private Boolean delBtn;

    private List<Column> column;

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getHeaderAlign() {
        return headerAlign;
    }

    public void setHeaderAlign(String headerAlign) {
        this.headerAlign = headerAlign;
    }

    public Boolean getAddBtn() {
        return addBtn;
    }

    public void setAddBtn(Boolean addBtn) {
        this.addBtn = addBtn;
    }

    public Boolean getDelBtn() {
        return delBtn;
    }

    public void setDelBtn(Boolean delBtn) {
        this.delBtn = delBtn;
    }

    public List<Column> getColumn() {
        return column;
    }

    public void setColumn(List<Column> column) {
        this.column = column;
    }
}
