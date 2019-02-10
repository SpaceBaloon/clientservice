package com.clientservice.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

/**
 *
 * @author BelkinSergei
 */
@Pattern( regexp = "^[#№]?\\s*[a-zA-Z0-9-\\s]+$" )
@Target(value = {ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {})
public @interface ArrestNumberPattern {
    
    public String message() default "{arrestNumberPattern.validation.pattern}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
    
}
