package com.sdt.common.bean;

/**
 * 分页实体
 *
 * @author shihaoran
 * @date 2020/3/4
 */
public class PagerBean {

    private int startPage;

    private int limit;

    public int getStartPage() {
        return (startPage - 1) * limit;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getLimit() {
        return limit * startPage;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
