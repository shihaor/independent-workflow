package com.sdt.common.result;

/**
 * @author shihaoran
 * @date 2020/3/9
 */
public class Result<T> {

    /**
     * 标识码
     */
    private int code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 成功状态，数据返回
     *
     * @param data 返回的数据
     */
    private Result(T data) {
        this.code = 0;
        this.msg = "成功";
        this.data = data;
    }

    /**
     * 失败状态，返回错误码
     *
     * @param codeMsg 错误信息
     */
    private Result(CodeMsg codeMsg) {
        if (null == codeMsg) {
            return;
        }
        this.code = codeMsg.getCode();
        this.msg = codeMsg.getMsg();
    }

    /**
     * 如果数据获取成功
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(data);
    }

    /**
     * 如果数据获取不成功
     */
    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
