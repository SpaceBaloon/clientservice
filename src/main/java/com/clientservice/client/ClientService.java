package com.clientservice.client;

import com.clientservice.misc.CertificateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author BelkinSergei
 */
@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;
    
    public Client findUniqueOneCaseInsensitive( String firstName, String lastName,
            CertificateType docType, String numberSeries ) {
        return repository.findUniqueOneCaseInsensitive(firstName, lastName, docType, numberSeries);
    }
    
}
