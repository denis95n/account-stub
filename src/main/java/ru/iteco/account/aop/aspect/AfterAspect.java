package ru.iteco.account.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class AfterAspect {

    @AfterReturning(value = "allGetMethod()", returning = "result")
    public void afterAllGetMethodAdvice(JoinPoint joinPoint, Object result) {
        log.info("afterAllGetMethodAdvice: After {} with result: {}", joinPoint.getSignature().toShortString(), result);
    }

    @AfterThrowing(value = "allSaveMethod()", throwing = "exception")
    public void afterSaveMethodThrowAdvice(JoinPoint joinPoint, Exception exception) {
        log.info("afterSaveMethodThrowAdvice: Method {} return Exception: {}", joinPoint.getSignature().toShortString(), exception.toString());
    }

    @After("allSaveMethod() || allGetMethod()")
    public void afterAllSaveAndGetMethodAdvice(JoinPoint joinPoint) {
        log.info("afterAllSaveAndGetMethodAdvice: Method {} with args: {}", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @Pointcut("execution(* save*(..))")
    public void allSaveMethod() {}

    @Pointcut("execution(* get*(..))")
    public void allGetMethod() {}

}
