package com.coursecodingshuttle.module1introduction.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumberValidation, Integer> {
    @Override
    public boolean isValid(Integer num, ConstraintValidatorContext context) {
        if(num == null){
            return true;
        }
        if(num < 2){
            return false;
        }
        for(int i = 2; i <= num/2; i++){
            if(num % i == 0){
                return false;
            }
        }
        return true;
    }
}
