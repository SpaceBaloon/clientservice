package com.clientservice.agency;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author BelkinSergei
 */
public interface AgencyRepository extends JpaRepository<Agency, Integer>{
    Agency findByCodeWithDetails( int code );
}
