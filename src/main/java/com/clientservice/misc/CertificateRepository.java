package com.clientservice.misc;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author BelkinSergei
 */
public interface CertificateRepository extends JpaRepository<Certificate, Integer>{
    
    Certificate findUniqueOne( Integer agency, Integer id );
    
}
