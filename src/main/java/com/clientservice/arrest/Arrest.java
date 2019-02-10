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
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
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
@NamedQueries(
        @NamedQuery( name = "Arrest.findUniqueOne", 
                query = "SELECT a FROM Arrest a WHERE a.agency = ?1 AND a.client = ?2 "
                        + " AND a.date = ?3 AND a.number = ?4"
        )
)
@Table( name = "AREST", 
        uniqueConstraints = @UniqueConstraint( columnNames = { "DATE", "NUMBER" } )
)
public class Arrest {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    
    @NotNull
    @OneToOne
    @JoinColumn( name = "AGENCY_ID" )
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

    public Arrest(Agency agency, LocalDate date, String number, BigDecimal amount, 
            ArestStatus status, Client client) {
        this.agency = agency;
        this.date = date;
        this.number = number;
        this.amount = amount;
        this.status = status;
        this.client = client;
    }

    public Arrest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    @Override
    public int hashCode() {
        int hash = 13;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.agency);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.number);
        hash = 53 * hash + Objects.hashCode(this.basis);
        hash = 53 * hash + Objects.hashCode(this.amount);
        hash = 53 * hash + Objects.hashCode(this.status);
        hash = 53 * hash + Objects.hashCode(this.client);
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
        final Arrest other = (Arrest) obj;
        if (!Objects.equals(this.number, other.number)) {
            return false;
        }
        if (!Objects.equals(this.basis, other.basis)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.agency, other.agency)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Arrest:[ " + "id=" + id 
                + ", agency=" + agency 
                + ", date=" + date 
                + ", number=" + number 
                + ", basis=" + basis 
                + ", amount=" + amount 
                + ", status=" + status
                + ", client=" + client
                + " ]";
    }
    
}
