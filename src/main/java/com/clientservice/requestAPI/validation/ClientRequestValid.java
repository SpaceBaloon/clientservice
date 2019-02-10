package com.clientservice.requestAPI.validation;

import com.clientservice.requestAPI.validation.validators.ClientRequestValidator;
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
@Target( ElementType.TYPE )
@Constraint( validatedBy = ClientRequestValidator.class )
public @interface ClientRequestValid {
    String message() default "Request not valid.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
