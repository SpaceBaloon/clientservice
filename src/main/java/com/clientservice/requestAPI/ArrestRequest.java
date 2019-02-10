package com.clientservice.requestAPI;

import com.clientservice.requestAPI.validation.ArrestOperationFieldValid;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author BelkinSergei
 */
public class ArrestRequest {    
    
    @NotNull( message = "{arrestRequest.docDate.validation.notNull}" )
    @JsonFormat( pattern = "dd.MM.yyyy" )
    @JsonDeserialize( using = LocalDateDeserializer.class )
    @JsonSerialize( using = LocalDateSerializer.class )
    private LocalDate docDate;
    
    @NotBlank( message = "{arrestRequest.docNum.validation.notBlank}" )
    @Column( name = "NUMBER", length = 30 )
    @Pattern( regexp = "^[#№]?\\s*[a-zA-Z0-9-\\s]+$", message = "{arrestRequest.docNum.validation.pattern}" )
    private String docNum;
    
    @Size( max = 1000, message = "{arrestRequest.purpose.validation.size}" )
    private String purpose;
    
    @NotNull( message = "{arrestRequest.amount.validation.notNull}" )
    private Long amount;
    
    private String refDocNum;
    
    @ArrestOperationFieldValid
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
    
}
