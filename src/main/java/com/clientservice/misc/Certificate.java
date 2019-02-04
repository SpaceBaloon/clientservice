package com.clientservice.misc;

import com.clientservice.agency.Agency;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity that represents agency certificate data in the DB.
 *
 * @author BelkinSergei
 */
@Entity
@Table( name = "CERTIFICATE" )
public class Certificate {
    
    @Id
    @Column( name = "ID" )
    private int id;
    
    @Column( name = "CERTIFICATE_NAME" )
    private String name;
    
    @Column( name = "PATTERN" )
    private String pattern;
    
    @Column( name = "MATCHPATTERN" )
    private String matchPattern;
    
    @ManyToOne
    @JoinColumn( name = "AGENCY" )
    @JsonIgnore
    private Agency agency;
    
    @Enumerated(EnumType.ORDINAL)
    @Column( name = "CONVERSION_TYPE" )
    private CertificateType type;

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

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
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
    
}
