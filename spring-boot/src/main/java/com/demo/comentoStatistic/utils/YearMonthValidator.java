package com.demo.comentoStatistic.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class YearMonthValidator implements ConstraintValidator<YearMonthConstraint, String> {
    private static final int MIN_YEAR_MONTH = 202201;
    private static final int MAX_YEAR_MONT = 202407;

    @Override
    public void initialize(YearMonthConstraint yearMonth){
    }

    @Override
    public boolean isValid(String yearMonth, ConstraintValidatorContext context){
        if(yearMonth == null)
            return false;
        if(!yearMonth.matches("\\d{6}"))
            return false;
        int yearMonthInt = Integer.parseInt(yearMonth);
        return yearMonthInt >= MIN_YEAR_MONTH & yearMonthInt <= MAX_YEAR_MONT;
    }

}
