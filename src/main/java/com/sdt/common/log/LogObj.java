package com.sdt.common.log;

/**
 * 日志对象
 *
 * @author shihaoran
 * @date 2020/3/9
 */
public class LogObj {

    /**
     * 行为
     */
    private String action;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 当前时间
     */
    private String date;

    /**
     * 客户端ip地址
     */
    private String remoteIp;

    /**
     * 日志信息
     */
    private Object info = null;

    /**
     * 结果
     */
    private boolean result;

    public LogObj() {
    }

    public LogObj(String action, long userId, String date, String remoteIp, boolean result, Object info) {
        this.action = action;
        this.userId = userId;
        this.date = date;
        this.remoteIp = remoteIp;
        this.info = info;
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "LogObj{" +
                "action='" + action + '\'' +
                ", userId=" + userId +
                ", date=" + date +
                ", remoteIp='" + remoteIp + '\'' +
                ", info=" + info +
                '}';
    }
}
