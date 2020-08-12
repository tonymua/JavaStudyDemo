package com.example.demo.Exception;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:
 * @date: created in 10:21 2020/8/11
 * @version:
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionInterceptor {
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletRequest request, Exception e) {
        String failMsg = null;
        if (e instanceof MethodArgumentNotValidException) {
            // 拿到参数校验具体异常信息提示
            // .map(FieldError::getDefaultMessage)
            failMsg = ((MethodArgumentNotValidException)e).getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.toList()).toString();
        }
        return failMsg;
    }
}