package com.clientservice.misc;

import com.clientservice.agency.Agency;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Entity that represents agency certificate data in the DB.
 *
 * @author BelkinSergei
 */
@Entity
@NamedQueries({
    @NamedQuery( name = "Certificate.findUniqueOne",
            query = "SELECT c FROM Certificate c WHERE c.agency.id = ?1 "
                    + " AND c.id = ?2"
    )
})
@Table( name = "CERTIFICATE",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "ID", "AGENCY" }
        )
)
public class Certificate implements Serializable {
    
    static final long serialVersionUID = 12931297871L;
    
    @Id
    @Column( name = "ID" )
    private Integer id;
    
    @Column( name = "CERTIFICATE_NAME" )
    private String name;
    
    @NotBlank( message = "Certificate.conversionPattern {validation.notEmpty}" )
    @Column( name = "CONVERSION_PATTERN" )
    private String conversionPattern;
    
    @NotBlank( message = "Certificate.matchPattern {validation.notEmpty}" )
    @Column( name = "MATCH_PATTERN" )
    private String matchPattern;
    
    @NotNull( message = "Certificate.agency {validation.notNull}" )
    @ManyToOne
    @JoinColumn( name = "AGENCY" )
    private Agency agency;
    
    @Enumerated(EnumType.ORDINAL)
    @Column( name = "CONVERSION_TYPE" )
    private CertificateType type;

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

    public String getConversionPattern() {
        return conversionPattern;
    }

    public void setConversionPattern(String conversionPattern) {
        this.conversionPattern = conversionPattern;
    }

    public String getMatchPattern() {
        return matchPattern;
    }

    public void setMatchPattern(String matchPattern) {
        this.matchPattern = matchPattern;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public CertificateType getType() {
        return type;
    }

    public void setType(CertificateType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.conversionPattern);
        hash = 59 * hash + Objects.hashCode(this.matchPattern);
        hash = 59 * hash + Objects.hashCode(this.agency);
        hash = 59 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Certificate other = (Certificate) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.conversionPattern, other.conversionPattern)) {
            return false;
        }
        if (!Objects.equals(this.matchPattern, other.matchPattern)) {
            return false;
        }
        if (!Objects.equals(this.agency, other.agency)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Certificate: [" + "id=" + id 
                + ", name=" + name 
                + ", conversionPattern=" + conversionPattern 
                + ", matchPattern=" + matchPattern 
                + ", agency=" + agency 
                + ", type=" + type 
                + ']';
    }
    
}
