package com.coursecodingshuttle.module1introduction.dto;

import com.coursecodingshuttle.module1introduction.annotations.EmployeeRoleValidation;
import com.coursecodingshuttle.module1introduction.annotations.PrimeNumberValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long employeeID;

    @NotNull(message = "Required field")
    @Size(min= 2, max = 10)
    private String firstName;

    @Size(min = 2, max = 20)
    private String lastName;

    @Email(message = "Provide valid Email")
    private String email;

    @NotNull(message = "Salary cannot be null")
    @DecimalMin(value = "10000.50")
    @DecimalMax(value = "2000000.50")
    @Digits(integer = 6, fraction = 2, message = "The salary format should be something like 'XXXXXX-YY'")
    private Double salary;

    @NotNull
    @Min(18)
    @Max(value = 99, message = "Age cannot be below 18 and above 99")
    private Integer age;

    @NotNull
    @EmployeeRoleValidation
    private String role;

    @PrimeNumberValidation
    private Integer num;
}
