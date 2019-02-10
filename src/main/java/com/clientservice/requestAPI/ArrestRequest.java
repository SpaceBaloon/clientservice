package com.clientservice.requestAPI;

import com.clientservice.requestAPI.validation.ArrestOperationFieldValid;
import com.clientservice.validation.ArrestNumberPattern;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author BelkinSergei
 */
public class ArrestRequest {    
    
    @NotNull( message = "ArrestRequest.docDate {validation.notNull}" )
    @JsonFormat( pattern = "dd.MM.yyyy" )
    @JsonDeserialize( using = LocalDateDeserializer.class )
    @JsonSerialize( using = LocalDateSerializer.class )
    private LocalDate docDate;
    
    @NotBlank( message = "ArrestRequest.docNum {validation.notEmpty}" )
    @Column( name = "NUMBER", length = 30 )
    @ArrestNumberPattern
    private String docNum;
    
    @Size( max = 1000, message = "{arrestRequest.purpose.validation.size}" )
    private String purpose;
    
    @NotNull( message = "ArrestRequest.amount {validation.notNull}" )
    private Long amount;
    
    private String refDocNum;
    
    @ArrestOperationFieldValid( message = "{arrestRequest.operation.validation.arrestOperationFieldValid}" )
    private Integer operation;

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getRefDocNum() {
        return refDocNum;
    }

    public void setRefDocNum(String refDocNum) {
        this.refDocNum = refDocNum;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public ArrestRequest(LocalDate docDate, String docNum, String purpose, 
            Long amount, String refDocNum, Integer operation) {
        this.docDate = docDate;
        this.docNum = docNum;
        this.purpose = purpose;
        this.amount = amount;
        this.refDocNum = refDocNum;
        this.operation = operation;
    }

    public ArrestRequest() {
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.docDate);
        hash = 67 * hash + Objects.hashCode(this.docNum);
        hash = 67 * hash + Objects.hashCode(this.purpose);
        hash = 67 * hash + Objects.hashCode(this.amount);
        hash = 67 * hash + Objects.hashCode(this.refDocNum);
        hash = 67 * hash + Objects.hashCode(this.operation);
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
        final ArrestRequest other = (ArrestRequest) obj;
        if (!Objects.equals(this.docNum, other.docNum)) {
            return false;
        }
        if (!Objects.equals(this.purpose, other.purpose)) {
            return false;
        }
        if (!Objects.equals(this.refDocNum, other.refDocNum)) {
            return false;
        }
        if (!Objects.equals(this.docDate, other.docDate)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        if (!Objects.equals(this.operation, other.operation)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ArrestRequest: [ " + "docDate=" + docDate 
                + ", docNum=" + docNum 
                + ", purpose=" + purpose 
                + ", amount=" + amount 
                + ", refDocNum=" + refDocNum 
                + ", operation=" + operation 
                + " ]";
    }
    
}
