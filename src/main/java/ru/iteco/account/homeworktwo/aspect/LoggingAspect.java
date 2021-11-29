package ru.iteco.account.homeworktwo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Before("allMethodInService()")
    public void beforeAllMethodInServiceAdvice(JoinPoint joinPoint) {
        log.info("beforeAllMethodInServiceAdvice:: START {}", joinPoint.getSignature().toShortString());
    }

    @After("allMethodInService()")
    public void afterAllMethodInServiceAdvice(JoinPoint joinPoint) {
        log.info("afterAllMethodInServiceAdvice:: END {}", joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(value = "allMethodInService()", throwing = "exception")
    public void afterAllMethodInServiceThrowAdvice(JoinPoint joinPoint, Exception exception) {
        log.info("afterAllMethodInServiceThrowAdvice:: END {} WITH THROW: {}", joinPoint.getSignature().toShortString(), exception.toString());
    }

//    @Around("allMethodInService()")
//    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        try {
//
//        } catch (Exception e) {
//            log.error("ERROR", e);
//            throw e;
//            return null;
//        }
//    }

    @Pointcut("within(ru.iteco.account.homeworktwo.service.*)")
    public void allMethodInService() {};

}
