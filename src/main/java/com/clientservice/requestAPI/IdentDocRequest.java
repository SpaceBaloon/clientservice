package com.clientservice.requestAPI;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.time.LocalDate;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BelkinSergei
 */
public class IdentDocRequest {
    
    @NotNull( message = "{identDocRequest.type.validation.notNull}" )
    @Min( value = 0, message = "{identDocRequest.type.validation.min}" )
    private Integer type;
    
    @NotBlank( message = "{identDocRequest.numberSeries.validation.notBlank}" )
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
    
}
