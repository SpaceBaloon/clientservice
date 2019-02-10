package com.clientservice.arrest;

import com.clientservice.agency.Agency;
import com.clientservice.client.Client;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author BelkinSergei
 */
public interface ArrestRepository extends JpaRepository<Arrest, Long>{
    Arrest findUniqueOne( Agency agency, Client client, LocalDate date, String number );
}
