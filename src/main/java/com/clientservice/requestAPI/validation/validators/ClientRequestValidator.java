package com.clientservice.requestAPI.validation.validators;

import com.clientservice.agency.Agency;
import com.clientservice.agency.AgencyService;
import com.clientservice.misc.Certificate;
import com.clientservice.requestAPI.ClientRequest;
import com.clientservice.requestAPI.validation.ClientRequestValid;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author BelkinSergei
 */
public class ClientRequestValidator implements ConstraintValidator<ClientRequestValid, ClientRequest>{
    
    @Autowired
    private AgencyService agencyService;
    
    @Override
    public void initialize(ClientRequestValid constraintAnnotation) {
        
    }

    @Override
    public boolean isValid(ClientRequest value, ConstraintValidatorContext context) {
        //check organCode
        final Agency agency = agencyService.findByCodeWithDetails( value.getOrganCode() );
        if( agency == null ) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "Agency with code="
                    + value.getOrganCode() + " doesn't exist." ).addConstraintViolation();
            return false;
        }
        //check identDoc.type
        final Integer identDocType = value.getIdentDoc().getType();
        Certificate fromDoc = null;
        if( agency.getCertificates() == null || agency.getCertificates().isEmpty() ) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "Certificates for agency with code="
                    + value.getOrganCode() + " were not specified." ).addConstraintViolation();
            return false;
        }
        for( Certificate e : agency.getCertificates() )
            if( Objects.equals( e.getId(), identDocType ) ) {
                fromDoc = e;
                break;
            }
        if( fromDoc == null ) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "There is no such docType="
                    + identDocType ).addConstraintViolation();
            return false;
        }
        //check identDoc.number format
        if(  !value.getIdentDoc().getNumberSeries().matches( fromDoc.getMatchPattern() ) ) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "IdentDoc numberSeries="
                    + value.getIdentDoc().getNumberSeries() + " doesn't match pattern="
                    + fromDoc.getMatchPattern() ).addConstraintViolation();
            return false;
        }
        //check arrest.refDocNumber
        final Integer operation = value.getArrest().getOperation();
        if( 1 < operation && operation < 4
                &&
                !value.getIdentDoc().getNumberSeries().equals( value.getArrest().getRefDocNum() )
                ) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate( "IdentDoc.numberSeries="
                    + value.getIdentDoc().getNumberSeries() 
                    + " must be equals with Arrest.refDocNumber="
                    + value.getArrest().getRefDocNum() ).addConstraintViolation();
            return false;
        }
        return true;
    }
    
}
