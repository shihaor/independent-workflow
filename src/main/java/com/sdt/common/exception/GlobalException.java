package com.sdt.common.exception;

import com.sdt.common.result.CodeMsg;

/**
 * 全局的业务异常实现类
 *
 * @author shihaoran
 * @date 2020/3/9
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

    public void setCodeMsg(CodeMsg codeMsg) {
        this.codeMsg = codeMsg;
    }
}
