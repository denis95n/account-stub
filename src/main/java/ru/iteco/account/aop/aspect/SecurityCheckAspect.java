package ru.iteco.account.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.account.aop.repository.User;

import java.util.List;

@Aspect
@Component
@Slf4j
public class SecurityCheckAspect {

    @Value("#{'${users-accept}'.split(',')}")
    private List<String> usersAccept;

    @Around(value = "allGetMethodWithUser(user)")
    public Object aroundAllGetMethodCheckAdvice(ProceedingJoinPoint proceedingJoinPoint, User user) throws Throwable {
        log.info("aroundAllGetMethodCheckAdvice: Security check method {} and User: {}", proceedingJoinPoint.getSignature().toShortString(), user);
        if (usersAccept.contains(user.getName())) {
            log.info("User: {} accept!", user);
            return proceedingJoinPoint.proceed();
        } else {
            throw new RuntimeException("В доступе отказано!");
        }
    }

    @Pointcut("execution(* get*(.., ru.iteco.account.aop.repository.User,..)) && args(user, ..)")
    public void allGetMethodWithUser(User user) {}


}
