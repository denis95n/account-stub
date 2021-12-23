package ru.iteco.account.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = StatusValidator.class)
public @interface Status {

    String message() default "Некорректный статус"; //ключ ValidationMessages.properties
    Class<?>[] groups() default { }; //группа проверки
    Class<? extends Payload>[] payload() default { }; //полезная нагрузка
}
