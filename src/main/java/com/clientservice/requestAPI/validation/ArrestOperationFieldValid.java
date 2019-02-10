package com.clientservice.requestAPI.validation;

import com.clientservice.requestAPI.validation.validators.ArrestOperationFieldValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author BelkinSergei
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.FIELD )
@Constraint( validatedBy = ArrestOperationFieldValidator.class )
public @interface ArrestOperationFieldValid {
    String message() default "{arrestRequest.operation.validation.arrestOperationFieldValid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
