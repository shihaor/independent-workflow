package com.sdt.common.handler;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 日志记录
 *
 * @author shihaoran
 * @date 2020/3/17
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface LogDocumentary {

    /**
     * true 启用
     */
    boolean value() default true;

    /**
     * 行为
     */
    String action() default "无";
}
