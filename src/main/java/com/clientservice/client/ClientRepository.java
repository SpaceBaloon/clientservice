package com.clientservice.client;

import com.clientservice.misc.CertificateType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author BelkinSergei
 */
public interface ClientRepository extends JpaRepository<Client, Long>{
    
    Client findUniqueOneCaseInsensitive( String firstName, String lastName,
            CertificateType docType, String numberSeries 
    );
    
    Client findUniqueCaseDetailed( String firstName, String lastName,
            CertificateType docType, String numberSeries 
    );
    
}
