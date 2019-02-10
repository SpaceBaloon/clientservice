package com.clientservice.requestAPI;

import com.clientservice.requestAPI.validation.ClientRequestValid;
import com.clientservice.requestAPI.validation.misc.ClassesCheck;
import java.util.Objects;
import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author BelkinSergei
 */
@GroupSequence( {ClientRequest.class, ClassesCheck.class} )
@ClientRequestValid( groups = ClassesCheck.class )
public class ClientRequest {
    
    @NotBlank( message = "ClientRequest.requestId {validation.notEmpty}" )
    private String requestId;
    
    @NotBlank( message = "ClientRequest.lastName {validation.notEmpty}" )
    private String lastName;
    
    @NotBlank( message = "ClientRequest.firstName {validation.notEmpty}" )
    private String firstName;
    
    @NotNull( message = "ClientRequest.organCode {validation.notNull}" )
    private Integer organCode;
    
    @NotNull( message = "ClientRequest.identDoc {validation.notNull}" )
    @Valid()
    private IdentDocRequest identDoc;
    
    @NotNull( message = "ClientRequest.arrest {validation.notNull}" )
    @Valid
    private ArrestRequest arrest;
    
    public ClientRequest() {
    }

    public ClientRequest(String requestId, String lastName, String firstName, 
            Integer organCode, IdentDocRequest identDoc, ArrestRequest arrest) {
        this.requestId = requestId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.organCode = organCode;
        this.identDoc = identDoc;
        this.arrest = arrest;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public Integer getOrganCode() {
        return organCode;
    }

    public void setOrganCode(Integer organCode) {
        this.organCode = organCode;
    }

    public IdentDocRequest getIdentDoc() {
        return identDoc;
    }

    public void setIdentDoc(IdentDocRequest identDoc) {
        this.identDoc = identDoc;
    }

    public ArrestRequest getArrest() {
        return arrest;
    }

    public void setArrest(ArrestRequest arrest) {
        this.arrest = arrest;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.requestId);
        hash = 67 * hash + Objects.hashCode(this.lastName);
        hash = 67 * hash + Objects.hashCode(this.firstName);
        hash = 67 * hash + Objects.hashCode(this.organCode);
        hash = 67 * hash + Objects.hashCode(this.identDoc);
        hash = 67 * hash + Objects.hashCode(this.arrest);
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
        final ClientRequest other = (ClientRequest) obj;
        if (!Objects.equals(this.requestId, other.requestId)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.organCode, other.organCode)) {
            return false;
        }
        if (!Objects.equals(this.identDoc, other.identDoc)) {
            return false;
        }
        if (!Objects.equals(this.arrest, other.arrest)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ClientRequest: [ " + "requestId=" + requestId 
                + ", lastName=" + lastName 
                + ", firstName=" + firstName 
                + ", organCode=" + organCode 
                + ", identDoc=" + identDoc 
                + ", arrest=" + arrest 
                + " ]";
    }
    
}
