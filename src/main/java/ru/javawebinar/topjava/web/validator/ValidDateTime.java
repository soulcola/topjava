package ru.javawebinar.topjava.web.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateTimeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateTime {
    String message() default "DateTime must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
