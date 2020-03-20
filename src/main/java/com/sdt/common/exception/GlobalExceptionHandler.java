package com.sdt.common.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sdt.common.handler.LogDocumentary;
import com.sdt.common.log.LogGenerator;
import com.sdt.common.result.CodeMsg;
import com.sdt.common.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 全局异常捕获
 *
 * @author shihaoran
 * @date 2020/3/9
 */
@Order(2)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Value("${session.userId}")
    private String user;

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception e) throws ClassNotFoundException, JsonProcessingException {
        // 控制台打印则加上
        e.printStackTrace();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String name = e.getStackTrace()[0].getMethodName();
        Method[] methods = Class.forName(e.getStackTrace()[0].getClassName()).getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().equals(name)) {
                boolean annotationPresent = methods[i].isAnnotationPresent(LogDocumentary.class);
                request.getSession().getAttribute(user);
                String ip = request.getRemoteAddr();
                if (annotationPresent) {
                    if (methods[i].getAnnotation(LogDocumentary.class).value()) {
                        LogGenerator.getLog(ip, 1L, methods[i].getAnnotation(LogDocumentary.class).action(), false, name);
                    }
                }
            }
        }
        if (e instanceof GlobalException) {
            GlobalException globalException = (GlobalException) e;
            return Result.error(globalException.getCodeMsg());
        } else if (e instanceof BindException) {
            BindException bind = (BindException) e;
            List<ObjectError> errors = bind.getAllErrors();
            ObjectError objectError = errors.get(0);
            String message = objectError.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(message));
        }
        return Result.error(CodeMsg.SERVER_ERROR);
    }
}
