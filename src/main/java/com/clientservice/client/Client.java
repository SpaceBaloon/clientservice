package com.clientservice.client;

import com.clientservice.validation.IdentDocValid;
import com.clientservice.arrest.Arrest;
import com.clientservice.misc.IdentDoc;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

/**
 * Entity that represents client in the DB.
 *
 * @author BelkinSergei
 */
@Entity
@Table( name = "CLIENT", 
        uniqueConstraints = @UniqueConstraint( columnNames = {
            "LASTNAME", "FIRSTNAME", "DOCTYPE", "NUMBERSERIES"
        } )
)
@NamedQueries ({ 
    @NamedQuery( name = "Client.findUniqueOneCaseInsensitive",
            query = "SELECT c FROM Client c WHERE UPPER( c.firstName ) = UPPER( ?1 ) "
                    + " and UPPER( c.lastName ) = UPPER( ?2 ) "
                    + " and c.identDoc.docType = ?3 "
                    + " and c.identDoc.numberSeries = ?4 "
    ),
    @NamedQuery( name = "Client.findUniqueCaseDetailed",
            query = "SELECT c FROM Client c LEFT JOIN FETCH c.arrests "
                    + " WHERE UPPER( c.firstName ) = UPPER( ?1 ) "
                    + " and UPPER( c.lastName ) = UPPER( ?2 ) "
                    + " and c.identDoc.docType = ?3 "
                    + " and c.identDoc.numberSeries = ?4 "
    )
})
public class Client implements Serializable {
    
    final static long serialVersionUID = 123712371982730L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column(name = "ID")
    private Long id;

    public Client( String firstName, String lastName, IdentDoc identDoc) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.identDoc = identDoc;
    }

    public Client() {
    }
    
    @NotBlank
    @Column( name = "LASTNAME", length = 100 )
    private String lastName;
    
    @NotBlank
    @Column( name = "FIRSTNAME", length = 100 )
    private String firstName;
    
    @Embedded
    @IdentDocValid
    private IdentDoc identDoc;
    
    @Column( name = "BIRTHDATE" )
    @JsonFormat( pattern = "dd.MM.yyyy" )
    @JsonDeserialize( using = LocalDateDeserializer.class )
    @JsonSerialize( using = LocalDateSerializer.class )
    private LocalDate birthDate;
    
    @Column( name = "BIRTHPLACE", length = 250 )
    private String birthPlace;
    
    @OneToMany( mappedBy = "client", orphanRemoval = true )
    private List<Arrest> arrests = new ArrayList<>();
    
    /**
     * Saving names in certain format is highly needed because of unique constraint.
     */
    @PrePersist
    @PreUpdate
    public void prePersist() {
        firstName = firstName.substring( 0, 1 ).toUpperCase()
                + firstName.substring( 1 ).toLowerCase();
        lastName = lastName.substring( 0, 1 ).toUpperCase()
                + lastName.substring( 1 ).toLowerCase();
    }
    
    public void addArrest( Arrest arrest ) {
        arrests.add( arrest );
        arrest.setClient( this );
    }
    
    public void removeArrest( Arrest arrest ) {
        arrests.remove( arrest );
        arrest.setClient( null );
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public IdentDoc getIdentDoc() {
        return identDoc;
    }

    public void setIdentDoc(IdentDoc identDoc) {
        this.identDoc = identDoc;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public List<Arrest> getArrests() {
        return arrests;
    }

    public void setArrests(List<Arrest> arrests) {
        this.arrests = arrests;
    }

    @Override
    public String toString() {
        return "Client:[ " + "id=" + id
                + ", lastName=" + lastName
                + ", firstName=" + firstName
                + ", birthDate=" + birthDate
                + ", birthPlace=" + birthPlace
                + ", identDoc=" + identDoc
                + " ]";
    }

    @Override
    public boolean equals(Object obj) {
        if( obj == this ) return true;
        if( obj == null || !( obj instanceof Client ) ) return false;
        Client other = (Client) obj;
        if( this.id != other.id && ( this.id == null || !(this.id.equals( other.id ) ) ) )
            return false;
        if( this.lastName != other.lastName && ( this.lastName == null || !(this.lastName.equals( other.lastName ) ) ) )
            return false;
        if( this.firstName != other.firstName && ( this.firstName == null || !(this.firstName.equals( other.firstName ) ) ) )
            return false;
        if( this.birthDate != other.birthDate && ( this.birthDate == null || !(this.birthDate.equals( other.birthDate ) ) ) )
            return false;
        if( this.birthPlace != other.birthPlace && ( this.birthPlace == null || !(this.birthPlace.equals( other.birthPlace ) ) ) )
            return false;
        if( this.identDoc != other.identDoc && ( this.identDoc == null || !(this.identDoc.equals( other.identDoc ) ) ) )
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 97;
        hash = 13 * hash + ( id == null ? 0 : id.hashCode() );
        hash = 13 * hash + ( lastName == null ? 0 : lastName.hashCode() );
        hash = 13 * hash + ( firstName == null ? 0 : firstName.hashCode() );
        hash = 13 * hash + ( birthDate == null ? 0 : birthDate.hashCode() );
        hash = 13 * hash + ( birthPlace == null ? 0 : birthPlace.hashCode() );
        hash = 13 * hash + ( identDoc == null ? 0 : identDoc.hashCode() );
        return hash;
    }
    
}
