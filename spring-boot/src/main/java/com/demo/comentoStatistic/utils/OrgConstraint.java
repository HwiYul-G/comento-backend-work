package com.demo.comentoStatistic.utils;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OrgValidator.class)
public @interface OrgConstraint {
    String message() default "존재하지 않는 조직명(org)입니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
