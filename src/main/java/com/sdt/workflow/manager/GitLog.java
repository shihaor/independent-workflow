package com.sdt.workflow.manager;

/**
 * git提交记录
 *
 * @author shihaoran
 * @date 2020/2/25
 */
public class GitLog {

    private String date;

    private String msg;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
