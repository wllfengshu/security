package com.wllfengshu.security.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 日志的aop
 *
 * @author
 */
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * 当sql执行时间超过该值时，进行warn级别的打印
     */
    private static final long WARN_WHEN_OVER_TIME = 5 * 1000L;

    /**
     * 打印sql执行的时间
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution( * com.wllfengshu.security.dao.*.*(..))")
    public Object logSqlExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime;
        if (costTime > WARN_WHEN_OVER_TIME) {
            log.warn("execute sql : {} costTime: [{}] ms", joinPoint.getSignature().getName(), costTime);
        } else {
            log.info("execute sql : {} costTime: [{}] ms", joinPoint.getSignature().getName(), costTime);
        }
        return result;
    }

    /**
     * 打印Web请求的执行时间
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution( * com.wllfengshu.security.rest.*.*(..))")
    public Object webLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime;
        log.info("request: {} cost: {}", joinPoint.getSignature().getName(), costTime);
        return result;
    }

}
