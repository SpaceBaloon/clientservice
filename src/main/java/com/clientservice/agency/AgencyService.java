package com.clientservice.agency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author BelkinSergei
 */
@Service
public class AgencyService {
    
    @Autowired
    private AgencyRepository repository;
    
    public Agency findByCodeWithDetails( int code ) {
        return repository.findByCodeWithDetails( code );
    }
    
}
