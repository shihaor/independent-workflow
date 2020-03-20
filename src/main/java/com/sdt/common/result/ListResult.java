package com.sdt.common.result;

import java.util.List;

/**
 * 返回的结果是分页的list
 *
 * @author shihaoran
 * @date 2020/3/9
 */
public class ListResult {

    private List list;

    private int totalSize;

    private int startPage;

    private int limit;

    public ListResult() {
    }

    public ListResult(List list, int totalSize, int startPage, int limit) {
        this.list = list;
        this.totalSize = totalSize;
        this.startPage = startPage;
        this.limit = limit;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
