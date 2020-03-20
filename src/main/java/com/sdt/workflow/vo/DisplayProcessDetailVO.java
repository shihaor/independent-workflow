package com.sdt.workflow.vo;

import java.io.Serializable;

/**
 * 展示流程运行详细的VO
 *
 * @date 2019/11/19
 * @author shihaoran
 */
public class DisplayProcessDetailVO implements Serializable {

    private static final long serialVersionUID = 2117754018064413271L;

    /**
     * 处理环节
     */
    private String handleName;

    /**
     * 发送人
     */
    private String sendPerson;

    /**
     * 接收人
     */
    private String receivedPerson;

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;

    /**
     * 留办时间
     */
    private String waitTime;

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public String getSendPerson() {
        return sendPerson;
    }

    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
    }

    public String getReceivedPerson() {
        return receivedPerson;
    }

    public void setReceivedPerson(String receivedPerson) {
        this.receivedPerson = receivedPerson;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(String waitTime) {
        this.waitTime = waitTime;
    }
}
