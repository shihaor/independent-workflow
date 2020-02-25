package com.sdt.workflow.manager;

import java.util.Date;

/**
 * git提交记录
 *
 * @author shihaoran
 * @date 2020/2/25
 */
public class GitLog {

    private Date data;

    private String msg;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
