package com.sailfish.aop;

import com.sailfish.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 监控方法AOP
 *
 * @author XIAXINYU3
 * @date 2019.8.23
 */
@Aspect
@Component
@Slf4j
@ConditionalOnProperty(prefix = "devops.monitor", name = "enable", havingValue = "true")
public class MonitorAop {

    public MonitorAop() {
        log.error("MonitorAop is initializing");
    }

    @Around("execution(* com.crc.crcloud.steam.deploy.service..*.*(..))")
    public Object monitor(ProceedingJoinPoint pjp) throws Throwable {
        String targetClassName = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();
        String methodFullName = targetClassName + "." + methodName;
        Object result = null;
        try {
            log.info("Start invoking：{}", methodFullName);
            long begin = System.currentTimeMillis();
            result = pjp.proceed();
            long cost = System.currentTimeMillis() - begin;
            log.info("Finish invoking：{}, Cost：{} ms", methodFullName, cost);
        } catch (Exception e) {
            throw new AppException(e.getMessage());
        }
        return result;
    }
}
