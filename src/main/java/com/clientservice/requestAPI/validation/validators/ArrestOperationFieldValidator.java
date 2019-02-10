package com.clientservice.requestAPI.validation.validators;

import com.clientservice.requestAPI.validation.ArrestOperationFieldValid;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author BelkinSergei
 */
public class ArrestOperationFieldValidator 
        implements ConstraintValidator<ArrestOperationFieldValid, Integer>{

    @Override
    public void initialize(ArrestOperationFieldValid constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return Objects.nonNull( value ) && ( 0 < value && value < 4 );
    }
    
}
