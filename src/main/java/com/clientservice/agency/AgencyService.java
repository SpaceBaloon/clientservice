package com.clientservice.agency;

import com.clientservice.exceptions.InternalDataException;
import com.clientservice.exceptions.ObjectNotFoundException;
import com.clientservice.misc.Certificate;
import com.clientservice.misc.CertificateType;
import com.clientservice.misc.IdentDoc;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author BelkinSergei
 */
@Service
public class AgencyService {
    
    @PersistenceContext
    private EntityManager em;
    
    private AgencyRepository repository;

    public AgencyService(AgencyRepository repository) {
        this.repository = repository;
    }
    
    public List<Agency> getAllAgency() {
        return repository.findAll();
    }
    
    public Agency getAgencyByCode( int code ) {
        return repository.findById( code ).orElseThrow( () -> 
                new InternalDataException( "Agency with id=" + code + " not found." )  
        );
    }
        
    public boolean isMatchPattern( Agency agency, IdentDoc identDoc ) {
        Certificate cerificate = getCerificate(agency, identDoc);
        return identDoc.getNumberSeries().matches( cerificate.getMatchPattern() );
    }
    
    public Certificate getCerificate( Agency agency, IdentDoc identDoc ) {
        if( agency == null || identDoc == null )
            throw new InternalDataException( this.getClass().getName() + ": parameters can't be null." );
        List<Certificate> certificates = agency.getCertificates();
        return certificates.stream().filter( ( item ) -> {
            return item.getId() == identDoc.getType();
        } ).findFirst().orElseThrow( () -> new ObjectNotFoundException() );
    }
    
    public IdentDoc toInternalCertificate( IdentDoc identDoc, Certificate certificate ) {
        if( certificate == null || identDoc == null )
            throw new InternalDataException( this.getClass().getName() + ": parameters can't be null." );
        IdentDoc result = new IdentDoc();
        result.setDocType( certificate.getType() );
        result.setIssueDate( identDoc.getIssueDate() );
        switch( certificate.getType() ) {
            case FOREIGN_PASSPORT: {
                result.setNumberSeries( 
                        toInternalNumber( CertificateType.FOREIGN_PASSPORT.getFormat(), 
                                certificate.getPattern(), identDoc.getNumberSeries()
                                ) );
                break;
            }
            case PASSPORT: {
                result.setNumberSeries( 
                        toInternalNumber( CertificateType.PASSPORT.getFormat(), 
                                certificate.getPattern(), identDoc.getNumberSeries()
                                ) );
                break;
            }
        }
        return result;
    }
    
    public String toInternalNumber( String toPattern, String fromPattern, String text ) {
        StringBuffer result = new StringBuffer();
        Queue<Character> listN = new ArrayDeque<>();
        Queue<Character> listS = new ArrayDeque<>();
        int length = fromPattern.length();
        int textLength = text.length();        
        for( int i=0; i<length && i<textLength; i++ ) {
            char cur = fromPattern.charAt(i);
            if( Character.compare( 'N', cur ) == 0 )
                listN.add( text.charAt( i ) );
            else if( Character.compare( 'S', cur ) == 0 )
                listS.add( text.charAt( i ) );
        }
        length = toPattern.length();
        for( int i=0; i<length; i++ ) {
            char cur = toPattern.charAt( i );
            if( Character.compare( 'N', cur ) == 0 && !listN.isEmpty() ) {
                result.append( listN.remove( ) );
            } else if( Character.compare( 'S', cur ) == 0 && !listS.isEmpty() ) {
                result.append( listS.remove( ) );
            } else
                result.append( cur );
        }
        return result.toString();
    }
    
}
