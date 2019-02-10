package com.clientservice.misc;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Used by CLient to store certificate data.
 *
 * @author BelkinSergei
 */
@Embeddable
public class IdentDoc implements Serializable {
    
    @NotNull( message = "IdentDoc.docType {validation.notNull}" )
    @Enumerated(EnumType.ORDINAL)
    @Column( name = "DOCTYPE" )
    private CertificateType docType;
    
    @NotBlank( message = "IdentDoc.numberSeries {validation.notNull}")
    @Column( name = "NUMBERSERIES" )
    private String numberSeries;
    
    @Column( name = "ISSUEDATE" )
    @JsonFormat( pattern = "dd.MM.yyyy" )
    @JsonDeserialize( using = LocalDateDeserializer.class )
    @JsonSerialize( using = LocalDateSerializer.class )
    private LocalDate issueDate;

    public IdentDoc(CertificateType docType, String numberSeries) {
        this.docType = docType;
        this.numberSeries = numberSeries;
    }

    public IdentDoc() {
    }

    public CertificateType getDocType() {
        return docType;
    }

    public void setDocType(CertificateType docType) {
        this.docType = docType;
    }

    public String getNumberSeries() {
        return numberSeries;
    }

    public void setNumberSeries(String numberSeries) {
        this.numberSeries = numberSeries;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    
    @Override
    public String toString() {
        return "IdentDoc: [ " + "docType=" + docType.ordinal()
                + ", numberSeries=" + numberSeries
                + ", issueDate=" + issueDate
                + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if( this == obj ) return true;
        if( obj == null || !( obj instanceof IdentDoc ) )
            return false;
        IdentDoc other = (IdentDoc) obj;
        if( this.docType != other.docType && ( this.docType == null || !( this.docType.equals( other.docType ) ) ) )
            return false;
        if( this.numberSeries != other.numberSeries && ( this.numberSeries == null || !( this.numberSeries.equals( other.numberSeries ) ) ) )
            return false;
        if( this.issueDate != other.issueDate && ( this.issueDate == null || !( this.issueDate.equals( other.issueDate ) ) ) )
            return false;
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 113;
        hash = 79 * hash + ( docType == null ? 0 : docType.hashCode() );
        hash = 79 * hash + ( numberSeries == null ? 0 : numberSeries.hashCode() );
        hash = 79 * hash + ( issueDate == null ? 0 : issueDate.hashCode() );
        return hash;
    }
    
}
