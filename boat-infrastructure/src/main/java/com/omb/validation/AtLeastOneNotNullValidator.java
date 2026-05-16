package com.omb.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOneNotNull, Object> {

    private String[] fieldNames;

    @Override
    public void initialize(AtLeastOneNotNull constraintAnnotation) {
        this.fieldNames = constraintAnnotation.fieldNames();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        // If specific field names are provided, they are checked
        if (fieldNames != null && fieldNames.length > 0) {
            for (String fieldName : fieldNames) {
                try {
                    Field field = value.getClass().getDeclaredField(fieldName);
                    field.setAccessible(true);
                    Object fieldValue = field.get(value);
                    if (fieldValue != null) {
                        return true;
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    return false;
                }
            }
            return false;
        } else {
            // Otherwise, we check all the fields of the object
            for (Field field : value.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (field.get(value) != null) {
                        return true;
                    }
                } catch (IllegalAccessException e) {
                    return false;
                }
            }
            return false;
        }
    }

}
