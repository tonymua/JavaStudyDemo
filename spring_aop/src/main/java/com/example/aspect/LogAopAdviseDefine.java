package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAopAdviseDefine {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    // 定义一个Pointcut, 使用切点表达式函数来描述对哪些 Join point 使用 advise.
    @Pointcut("within(com.example.service..*)")
    public void poincut() {
        
    }
    
    // 定义advise
    @Before("poincut()")
    public void logMethodInvokeParam(JoinPoint joinPoint) {
        logger.info("---Before method {} invoke, param: {}---",
            joinPoint.getSignature().toShortString(),
            joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "poincut()",returning = "message")
    public void logMethodInvokeResult(JoinPoint joinPoint,Object message){
        logger.info("---After method {} invoke, result: {}---",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    @AfterThrowing(pointcut = "poincut()",throwing = "exception")
    public void logMethodInvokeException(JoinPoint joinPoint,Exception exception){
        logger.info("---method {} invoke exception: {}---",
                joinPoint.getSignature().toShortString(),
                exception.getMessage());
    }
}