package com.clientservice.client;

import com.clientservice.exceptions.InternalDataException;
import com.clientservice.misc.Certificate;
import com.clientservice.misc.CertificateRepository;
import com.clientservice.misc.GroupFormatter;
import com.clientservice.misc.IdentDoc;
import com.clientservice.requestAPI.ClientRequest;
import com.clientservice.requestAPI.IdentDocRequest;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author BelkinSergei
 */
@Service
public class ClientRequestConverter {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private CertificateRepository certificateRepository;
    
    public Client convert( ClientRequest request ) {
        Objects.requireNonNull( request );
        
        Client client;
        IdentDoc doc;
        
        doc = convertDocFromRequest( request.getOrganCode(), request.getIdentDoc() );
        
        client = clientRepository.findUniqueOneCaseInsensitive( 
                request.getFirstName(), request.getLastName(), doc.getDocType(), doc.getNumberSeries() );
        if( client == null ) {
            client = convertClientFromRequest( request );
            client.setIdentDoc( doc );
            client = clientRepository.save( client );
        }
        return client;
    }
    
    Client convertClientFromRequest( ClientRequest request ) {
        Client client = new Client();
        client.setFirstName( request.getFirstName() );
        client.setLastName( request.getLastName() );
        return client;
    }

    IdentDoc convertDocFromRequest( Integer organCode, IdentDocRequest request) {
        Certificate certificate = certificateRepository.findUniqueOne( organCode, request.getType() );
        if( certificate == null )
            throw new InternalDataException( "Certifcate for organCode=" + organCode
                    + " and IdentDoc.type=" + request.getType() + " was not found."
            );
        GroupFormatter converter = GroupFormatter.getForPattern( 
                certificate.getConversionPattern(), request.getNumberSeries() );
        IdentDoc identDoc = new IdentDoc( certificate.getType(), 
                converter.convertToPattern( certificate.getType().getConversionPattern() ) );
        identDoc.setIssueDate( request.getIssueDate() );
        return identDoc;
    }
    
}
