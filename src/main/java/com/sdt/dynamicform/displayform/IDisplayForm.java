package com.sdt.dynamicform.displayform;

import java.io.IOException;

/**
 * 展示表单的接口，可拓展其他业务查询表单
 *
 * @author shihaoran
 * @date 2020/3/16
 */
public interface IDisplayForm {

    /**
     * 展示表单
     *
     * @param param 参数
     * @return 表单
     * @throws IOException IO异常Json转化失败
     */
    Object displayForm(String param) throws IOException;
}
