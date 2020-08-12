package com.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class ExpiredAopAdviseDefine {
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Pointcut("within(com.example.service.impl.ExpiredServiceImpl)")
    public void pointcut() {
        
    }
    
    @Around("pointcut()")
    public Object methodInvokeExpiredTime(
        ProceedingJoinPoint proceedingJoinPoint) {
        try {
            // StopWatch 任务执行时间监视器
            StopWatch stopWatch = new StopWatch();
            // 开始
            stopWatch.start();
            Object proceed = proceedingJoinPoint.proceed();
            // 结束
            stopWatch.stop();
            // 上报到监控平台
            reportToMonitorSystem(
                proceedingJoinPoint.getSignature().toShortString(),
                stopWatch.getTotalTimeMillis());
            return proceed;
            
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
    
    public void reportToMonitorSystem(String methodName, long expiredTime) {
        logger.info("---method {} invoked, expired time: {} ms---",
            methodName,
            expiredTime);
    }
}