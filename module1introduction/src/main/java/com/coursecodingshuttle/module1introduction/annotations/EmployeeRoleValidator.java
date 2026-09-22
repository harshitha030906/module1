package com.coursecodingshuttle.module1introduction.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation, String> {

    @Override
    public boolean isValid(String input, ConstraintValidatorContext context) {
        List<String> employeeRoles = List.of("ADMIN", "USER");
        return employeeRoles.contains(input);
    }
}
