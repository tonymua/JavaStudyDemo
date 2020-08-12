package com.demo.aspect;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 记录请求访问日志
 *
 * @author: lwy
 * @date: created in 14:16 2020/7/29
 * @version: 1.0.0
 */

@Component
@Aspect
public class WebLogAspect {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("within(com.demo.controller..*)")
    public void webLog() {

    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint pjp) {
        try {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
            Object proceed = pjp.proceed();
            stopWatch.stop();
            logger.info("SPENDTIME:" + stopWatch.getTotalTimeMillis());
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes =
            (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("URL:" + request.getRequestURL().toString());
        logger.info("HTTP_METHOD:" + request.getMethod());
        logger.info("IP:" + request.getRemoteAddr());
        logger.info("CLASS_METHOD:" + joinPoint.getSignature().getDeclaringTypeName() + "."
            + joinPoint.getSignature().getName());
        logger.info("ARGS:" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "webLog()", returning = "response")
    public void doAfterReturning(Object response) {
        logger.info("RESPONSE:" + response);
    }
}