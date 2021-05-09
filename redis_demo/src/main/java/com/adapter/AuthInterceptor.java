package com.adapter;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.annotation.TokenAnnotation;
import com.service.TokenService;

/**
 * @author:
 * @date: created in 22:38 2021/3/25
 * @version:
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //被tokenAnnotation标记的扫描
        TokenAnnotation methodAnnotation = method.getAnnotation(TokenAnnotation.class);
        if (methodAnnotation != null) {
            try {
                return tokenService.checkToken(request);// 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
            } catch (Exception ex) {
                throw new RuntimeException("token校验失败！");
            }
        }
        return true;
    }
}