package ru.iteco.account.ioc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

@Component
public class InjectRandomBeanPostProcessor implements BeanPostProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(InjectRandomBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        LOGGER.info("postPocessBeforeInitialization: beanName: {}, beanClass: {}", beanName, bean.getClass().getSimpleName());

        Field[] declaredFields = bean.getClass().getDeclaredFields();

        for (Field declaredField: declaredFields) {
            if (declaredField.isAnnotationPresent(InjectRandom.class)) {
                declaredField.setAccessible(true);
                InjectRandom annotation = declaredField.getAnnotation(InjectRandom.class);
                int random = getRandomInt(annotation.min(), annotation.max());
                LOGGER.info("Set random value in {}. Value: {}", declaredField.getName(), random);
                ReflectionUtils.setField(declaredField, bean, random);
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private int getRandomInt(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

}
