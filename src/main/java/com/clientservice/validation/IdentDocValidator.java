package com.clientservice.validation;

import com.clientservice.misc.CertificateType;
import com.clientservice.misc.IdentDoc;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author BelkinSergei
 */
public class IdentDocValidator implements ConstraintValidator<IdentDocValid, IdentDoc>{
    
    @Override
    public boolean isValid(IdentDoc value, ConstraintValidatorContext context) {
        if( value.getDocType() == null || value.getNumberSeries() == null 
                || value.getNumberSeries().isEmpty() ) return false;
        if( value.getDocType().equals( CertificateType.FOREIGN_PASSPORT ) ) 
            return value.getNumberSeries().matches( CertificateType.FOREIGN_PASSPORT.getMatchPattern() );
        if( value.getDocType().equals( CertificateType.PASSPORT ) )  
            return value.getNumberSeries().matches( CertificateType.PASSPORT.getMatchPattern() );
        return false;
    }
    
}
