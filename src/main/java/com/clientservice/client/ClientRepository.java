package com.clientservice.client;

import com.clientservice.misc.CertificateType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author BelkinSergei
 */
public interface ClientRepository extends JpaRepository<Client, Long>{
    
    List<Client> findByLastNameAndFirstNameAndIdentDocDocTypeAndIdentDocNumberSeries( 
            String lastName, String firstName, CertificateType type, String number
    );
    
}
