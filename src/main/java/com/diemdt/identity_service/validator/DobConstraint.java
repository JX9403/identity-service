package com.diemdt.identity_service.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;
import java.util.List;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
@Constraint(
        validatedBy = {}
)
public @interface DobConstraint {
    String message() default "Invalid date of birth";

    int min() ;
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
