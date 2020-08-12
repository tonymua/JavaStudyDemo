package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class HttpAopAdviseDefine {

    //定义一个 Pointcut, 使用切点表达式函数来描述对哪些Join point使用advice.
    @Pointcut("@annotation(com.example.annotation.AuthChecker)")
    public void pointcut(){

    }

    //定义advice
    @Around("pointcut()")
    public Object checkAuth(ProceedingJoinPoint proceedingJoinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //检查用户所传递的 token 是否合法
        String token = getToken(request);
        if (!token.equalsIgnoreCase("111")){
            return "token不合法！";
        }
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    private String getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "";
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equalsIgnoreCase("token")) {
                return cookie.getValue();
            }
        }
        return "";
    }
}