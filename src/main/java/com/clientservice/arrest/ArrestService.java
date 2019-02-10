package com.clientservice.arrest;

import com.clientservice.agency.Agency;
import com.clientservice.agency.AgencyRepository;
import com.clientservice.client.Client;
import com.clientservice.exceptions.InternalDataException;
import com.clientservice.requestAPI.ArrestRequest;
import java.math.BigDecimal;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author BelkinSergei
 */
@Service
public class ArrestService {
    
    @Autowired
    private ArrestRepository arrestRepository;
    @Autowired
    private AgencyRepository agencyRepository;
    
    public Arrest saveFromRequest( Integer organCode, Client client, 
            ArrestRequest request ) {
        Agency agency = agencyRepository.findById( organCode ).orElseThrow( 
                () -> {
                    return new InternalDataException( "Agency with code " + organCode + " wasn't found." );
                }
        );
        Arrest arrest = arrestRepository.findUniqueOne( agency, client, 
                request.getDocDate(), request.getDocNum() );
        if( request.getOperation() == 1 ) {
            if( arrest != null ) 
                throw new InternalDataException( "Arrest " + request 
                        + " is already exists. Correct your operation code." );
            arrest = new Arrest( agency, request.getDocDate(), request.getDocNum(), 
                    BigDecimal.valueOf( request.getAmount() ), ArestStatus.ACTIVE, client );
        } else {
            if( arrest == null )
                throw new InternalDataException( "There is no such arrest " + request + "." );
            if( request.getOperation() == 2 ) {
                arrest.setAmount( BigDecimal.valueOf( request.getAmount() ) );
                arrest.setBasis( request.getPurpose() );
                if( 0 < request.getAmount() ) {
                    arrest.setStatus(ArestStatus.ACTIVE);
                } else {
                    arrest.setStatus(ArestStatus.CANCELED);
                }
            } else if( request.getOperation() == 3 ) {
                arrest.setStatus( ArestStatus.CANCELED );
            }
        }
        return arrestRepository.save( arrest );
    }
    
}
