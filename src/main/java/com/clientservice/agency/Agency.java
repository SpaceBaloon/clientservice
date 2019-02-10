package com.clientservice.agency;

import com.clientservice.misc.Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity that represents agency in the DB.
 *
 * @author BelkinSergei
 */
@Entity
@NamedQueries( {
    @NamedQuery( name = "Agency.findByCodeWithDetails",
            query = "SELECT a FROM Agency a LEFT JOIN FETCH a.certificates c "
                    + "WHERE a.id = ?1")
} )
@Table(name = "AGENCY")
public class Agency {
      
    @Id
    @Column( name = "ID" )
    private Integer id;
    
    @Column( name = "AGENCY_NAME" )
    private String name;
    
    @OneToMany( mappedBy = "agency" )
    private List<Certificate> certificates = new ArrayList<>();

    public Agency() {
        this.id = 0;
        this.name = null;
    }
    
    private Agency( Integer id, String name ) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Agency other = (Agency) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Agency: [ " + "id=" + id + ", name=" + name + " ]";
    }
    
}
