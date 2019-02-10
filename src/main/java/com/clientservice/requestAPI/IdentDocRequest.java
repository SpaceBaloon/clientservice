package com.clientservice.requestAPI;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BelkinSergei
 */
public class IdentDocRequest {
    
    @NotNull( message = "IdentDocRequest.type {validation.notNull}" )
    @Min( value = 0, message = "IdentDocRequest.type {validation.min}" )
    private Integer type;
    
    @NotBlank( message = "IdentDocRequest.numberSeries {validation.notEmpty}" )
    private String numberSeries;
    
    @JsonFormat( pattern = "dd.MM.yyyy" )
    @JsonDeserialize( using = LocalDateDeserializer.class )
    @JsonSerialize( using = LocalDateSerializer.class )
    private LocalDate issueDate;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public IdentDocRequest() {
    }

    public IdentDocRequest(Integer type, String numberSeries, LocalDate issueDate) {
        this.type = type;
        this.numberSeries = numberSeries;
        this.issueDate = issueDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 19 * hash + Objects.hashCode(this.type);
        hash = 19 * hash + Objects.hashCode(this.numberSeries);
        hash = 19 * hash + Objects.hashCode(this.issueDate);
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
        final IdentDocRequest other = (IdentDocRequest) obj;
        if (!Objects.equals(this.numberSeries, other.numberSeries)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.issueDate, other.issueDate)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "IdentDocRequest: [ " + "type=" + type 
                + ", numberSeries=" + numberSeries 
                + ", issueDate=" + issueDate 
                + " ]";
    }
    
}
