package com.clientservice.arrest;

import com.clientservice.agency.Agency;
import com.clientservice.client.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Entity that represents arrest in the DB.
 * There are some transient properties for request handling.
 *
 * @author BelkinSergei
 */
@Entity
@Table( name = "AREST" )
public class Arrest {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private long id;
    
    @NotNull
    @OneToOne
    private Agency agency;
    
    @NotNull
    @Column( name = "DATE" )
    @JsonFormat( pattern = "dd.MM.yyyy" )
    @JsonDeserialize( using = LocalDateDeserializer.class )
    @JsonSerialize( using = LocalDateSerializer.class )
    private LocalDate date;
    
    @NotBlank
    @Column( name = "NUMBER", length = 30 )
    @Pattern( regexp = "^[#№]?[\\w-]+$" )
    private String number;
    
    @Column( name = "BASIS", length = 1000 )
    private String basis;
    
    @NotNull
    @Column( name = "AMOUNT", precision = 18, scale = 0 )
    private BigDecimal amount;
    
    @NotNull
    @Enumerated( EnumType.STRING )
    @Column( name = "STATUS" )
    private ArestStatus status;
    
    @ManyToOne
    @JoinColumn( name = "CLIENT_ID" )
    private Client client;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public ArestStatus getStatus() {
        return status;
    }

    public void setStatus(ArestStatus status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    @Transient
    private int operation;

    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
    
}
