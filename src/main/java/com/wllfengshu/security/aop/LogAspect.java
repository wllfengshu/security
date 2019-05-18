package com.wllfengshu.security.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
     * 当sql执行时间超过该值时，进行warn级别的打印(单位ms)
     */
    private static final long SQL_WARN_WHEN_OVER_TIME = 5 * 1000;

    /**
     * 当web请求响应时间超过该值时，进行warn级别的打印(单位ms)
     */
    private static final long WEB_WARN_WHEN_OVER_TIME = 60 * 1000;

    /**
     * 当从redis中获取数据超过该值时，进行warn级别的打印(单位ms)
     */
    private static final long REDIS_WARN_WHEN_OVER_TIME = 100;

    private Object process(ProceedingJoinPoint joinPoint,String target,long warnTime) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - startTime;
        if (costTime > warnTime) {
            log.warn("execute "+target+":{} costTime:{} ms", joinPoint.getSignature().getName(), costTime);
        } else {
            log.info("execute "+target+":{} costTime:{} ms", joinPoint.getSignature().getName(), costTime);
        }
        return result;
    }

    /**
     * 打印sql执行的时间
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution( * com.wllfengshu.security.dao.*.*(..))")
    public Object sqlLogExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return process(joinPoint,"sql",SQL_WARN_WHEN_OVER_TIME);
    }

    /**
     * 打印Web请求的执行时间
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution( * com.wllfengshu.security.rest.*.*(..))")
    public Object webLogExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return process(joinPoint,"request",WEB_WARN_WHEN_OVER_TIME);
    }

    /**
     * 打印redis执行的时间
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution( * com.wllfengshu.security.component.*.*(..))")
    public Object redisLogExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        return process(joinPoint,"redis",REDIS_WARN_WHEN_OVER_TIME);
    }
}
