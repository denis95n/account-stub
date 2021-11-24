package ru.iteco.account.homeworkone;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

@Slf4j
public class PrintResultMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        final Method method = invocation.getMethod();
        boolean isAnnotationMethod = method.isAnnotationPresent(PrintResult.class);
        if (!isAnnotationMethod) {
            for (Method declaredMethod : invocation.getThis().getClass().getDeclaredMethods()) {
                if (method.getName().equals(declaredMethod.getName()) &&
                        AnnotationUtils.findAnnotation(declaredMethod, PrintResult.class) != null) {
                    isAnnotationMethod = true;
                    break;
                }
            }
        }

        Object proceed = invocation.proceed();

        if (isAnnotationMethod) {
            log.info("PrintResult: {}", proceed);
        }

        return proceed;
    }
}
