package com.clientservice.agency;

import com.clientservice.misc.Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity that represents agency in the DB.
 *
 * @author BelkinSergei
 */
@Entity
@Table(name = "AGENCY")
public class Agency {
      
    @Id
    @Column( name = "ID" )
    private int id;
    
    @Column( name = "AGENCY_NAME" )
    private String name;
    
    @OneToMany( mappedBy = "agency" )
    private List<Certificate> certificates = new ArrayList<>();

    public Agency() {
        this.id = 0;
        this.name = null;
    }
    
    private Agency( int id, String name ) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }
    
}
