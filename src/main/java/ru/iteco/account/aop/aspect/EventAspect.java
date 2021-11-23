package ru.iteco.account.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
@Order(1)
public class EventAspect {

    @Before("@annotation(ru.iteco.account.aop.aspect.AspectEvent)")
    public void beforeAllAnnotationAspectEventAdvice() {
        log.info("beforeAllAnnotationAspectEventAdvice: try call method mark @AspectEvent");
    }

}
