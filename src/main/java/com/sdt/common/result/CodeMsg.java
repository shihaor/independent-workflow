package com.sdt.common.result;

/**
 * @author shihaoran
 * @date 2020/3/9
 */
public class CodeMsg {

    /**
     * 通用异常
     */
    public static final CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static final CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务器异常");
    public static final CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常: %s");
    public static final CodeMsg ACCESS_LIMIT_REACHED = new CodeMsg(500102, "连接异常");
    public static final CodeMsg SESSION_ERROR = new CodeMsg(500103, "用户信息异常");
    public static final CodeMsg REQUEST_ERROR = new CodeMsg(500104, "用户请求异常");
    public static final CodeMsg PAGE_ERROR = new CodeMsg(500105, "页码异常");
    public static final CodeMsg FILE_DOWNLOAD_FAIL = new CodeMsg(500106, "文件下载失败, 文件转化异常");
    public static final CodeMsg TEST_ERROR = new CodeMsg(500107, "测试异常");
    public static final CodeMsg LOG_WRITE_ERROR = new CodeMsg(500108, "日志记录异常");


    /**
     * 表单异常
     */
    public static final CodeMsg FORM_SAVE_BUSINESS_NOT_EXIT = new CodeMsg(500200, "表单保存错误，因为业务对象不存在");
    public static final CodeMsg FORM_SAVE_REPEAT_BUSINESS = new CodeMsg(500201, "表单保存错误，请刷新页面，重复提交了业务对象");
    public static final CodeMsg FORM_SAVE_ERROR = new CodeMsg(500202, "表单保存异常");
    public static final CodeMsg DELETE_ERROR_BUSINESS_NOT_EXIST = new CodeMsg(500203, "表单删除错误，因为业务对象不存在");
    public static final CodeMsg OPERATOR_REPEAT = new CodeMsg(500204, "有人抢先一步比您操作了表单！");
    public static final CodeMsg FORM_IS_EMPTY = new CodeMsg(500205, "表单不存在");

    /**
     * 业务对象异常
     */
    public static final CodeMsg BUSINESS_IS_EMPTY = new CodeMsg(500300, "业务对象不存在");
    public static final CodeMsg BUSINESS_RELEASE_ERROR = new CodeMsg(500301, "业务对象关联异常");

    /**
     * 流程相关
     */
    public static final CodeMsg PROCESSINSTANCE_ID_NULL = new CodeMsg(500400, "流程实例参数不能为空");
    public static final CodeMsg DELETE_PROCESS_FAIL = new CodeMsg(500401, "删除流程实例失败");
    public static final CodeMsg ACTIVE_PROCESS_FAIL = new CodeMsg(500402, "流程激活失败");
    public static final CodeMsg SUSPEND_PROCESS_FAIL = new CodeMsg(500403, "流程挂起失败");
    public static final CodeMsg DELETE_DEPLOYMENT_FAIL = new CodeMsg(500404, "删除部署定义失败");
    public static final CodeMsg PROCESS_DETAIL_PARAM_ERROR = new CodeMsg(500405, "流程细节获取失败，流程参数有误，请联系管理员");

    /**
     * 路径相关问题
     */
    public static final CodeMsg DIR_NOT_FOUNT = new CodeMsg(500500, "路径为找到，请查实");

    /**
     * 登录人员相关问题
     */
    public static final CodeMsg PERSON_INFO_ERROR = new CodeMsg(500600, "登录的人员信息错误");

    /**
     * 标识码
     */
    private int code;

    /**
     * 信息
     */
    private String msg;


    public CodeMsg() {
    }

    public CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
