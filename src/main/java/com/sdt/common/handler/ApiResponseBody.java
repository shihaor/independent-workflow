package com.sdt.common.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.common.exception.GlobalException;
import com.sdt.common.log.LogGenerator;
import com.sdt.common.result.CodeMsg;
import com.sdt.common.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @author shihaoran
 * @date 2020/3/17
 */
@Order(1)
@RestControllerAdvice
public class ApiResponseBody implements ResponseBodyAdvice<Result> {

    @Value("${session.userId}")
    private String user;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        boolean annotationPresent = method.isAnnotationPresent(LogDocumentary.class);
        return annotationPresent;
    }

    @Override
    public Result beforeBodyWrite(Result result, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String action = methodParameter.getMethod().getAnnotation(LogDocumentary.class).action();
        String ip = serverHttpRequest.getRemoteAddress().getHostString();
        String name = methodParameter.getMethod().getName();
        try {
            if ((0 == result.getCode())) {
                LogGenerator.getLog(ip, 1L, action, true, name);
            } else {
                LogGenerator.getLog(ip, 1L, action, false, name);
            }
        } catch (JsonProcessingException e) {
            throw new GlobalException(CodeMsg.LOG_WRITE_ERROR);
        }
        return result;
    }
}

