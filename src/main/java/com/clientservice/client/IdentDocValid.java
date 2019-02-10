package com.clientservice.client;

import com.clientservice.misc.IdentDocValidator;
import java.lang.annotation.Documented;
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
@Constraint( validatedBy = IdentDocValidator.class )
@Documented
public @interface IdentDocValid {
    String message() default "Client identification documents should have "
            + " correct type and appropriate formatted number.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
