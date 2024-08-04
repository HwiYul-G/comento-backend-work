package com.demo.comentoStatistic.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class OrgValidator implements ConstraintValidator<OrgConstraint, String> {
    private final Set<String> ORG_SET = Set.of("AAA", "BBB", "CCC", "DDD");

    @Override
    public void initialize(OrgConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(String org, ConstraintValidatorContext constraintValidatorContext) {
        if (org == null) {
            return false;
        }
        return ORG_SET.contains(org);
    }
}
