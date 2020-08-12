package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
//    ThreadLocal<Long> startTime = new ThreadLocal<>();
    
    @Pointcut("within(com.example.controller..*)")
    public void webLog() {
        
    }
    
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) {
        
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Object proceed = proceedingJoinPoint.proceed();
            stopWatch.stop();
            logger.info("SPENDTIME:" + stopWatch.getTotalTimeMillis());
            return proceed;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
        
    }
    
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes servletRequestAttributes =
            (ServletRequestAttributes)RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        
//        startTime.set(System.currentTimeMillis());
        // 记录以下请求
        logger.info("URL:" + request.getRequestURL().toString());
        logger.info("HTTP_METHOD:" + request.getMethod());
        logger.info("IP:" + request.getRemoteAddr());
        logger.info(
            "ClASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName());
        logger.info("ARGS:" + Arrays.toString(joinPoint.getArgs()));
    }
    
    @AfterReturning(pointcut = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) {
        logger.info("RESPONSE:" + ret);
        // logger.info("SPEND_TIME:"+(System.currentTimeMillis()-startTime.get()));
    }
}