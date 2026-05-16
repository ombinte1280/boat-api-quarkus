package com.omb.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AtLeastOneNotNullValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneNotNull {

    String message() default "At least one field must be non-zero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Optional: List of field names to check.
     * If not specified, one can decide to check all fields or implement specific logic.
     */
    String[] fieldNames() default {};
}
