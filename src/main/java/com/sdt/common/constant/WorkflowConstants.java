package com.sdt.common.constant;

import java.awt.*;

/**
 * 工作流的常量类
 *
 * @author shihaoran
 * @date 2019/11/20
 */
public class WorkflowConstants {

    /**
     * 像素边距
     */
    public static final int PROCESS_PADDING = 5;

    /**
     * 颜色定义
     */
    public static final Color COLOR_NORMAL = new Color(0, 205, 0);
    public static final Color COLOR_CURRENT = new Color(255, 0, 0);

    /**
     * 发送人
     */
    public static final String SEND_PERSON = "sendPerson";

    /**
     * 发送单位
     */
    public static final String SEND_ORGANIZE = "sendOrganize";

    /**
     * 接收人
     */
    public static final String REC_PERSON = "receivedPerson";

    /**
     * 接收单位
     */
    public static final String REC_ORGANIZE = "receivedOrganize";

    /**
     * 备注
     */
    public static final String OPTIONAL = "optional";

    /**
     * 意见 是否接收
     */
    public static final String OPTION = "option";

    /**
     * 单据边列展示
     */
    public static final String SEND_ORGANIZE_SAVE = "一式三联第一联 发送单位留存";

    /**
     * 单据名称
     */
    public static final String INVOICE_NAME = "普通密码设备（配件）配发交接单";


    /**
     * 发送单位负责人
     */
    public static final String SNED_ORGANIZE_DUTY_PERSON = "sendOrganizeDutyPerson";

    /**
     * 接收单位负责人
     */
    public static final String REC_ORGANIZE_DUTY_PERSON = "recOrganizeDutyPerson";

    /**
     * 接收人电话
     */
    public static final String REC_PERSON_PHONE = "recPhone";
}
