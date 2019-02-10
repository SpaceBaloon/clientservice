package com.clientservice.requestAPI;

import com.clientservice.requestAPI.validation.ClientRequestValid;
import com.clientservice.requestAPI.validation.misc.ClassesCheck;
import com.clientservice.requestAPI.validation.misc.FieldsCheck;
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
    
    @NotBlank( message = "{clientRequest.requestId.validation.notBlank}" )
    private String requestId;
    
    @NotBlank( message = "{clientRequest.lastName.validation.notBlank}" )
    private String lastName;
    
    @NotBlank( message = "{clientRequest.firstName.validation.notBlank}" )
    private String firstName;
    
    @NotNull( message = "{clientRequest.organCode.validation.notNull}" )
    private Integer organCode;
    
    @NotNull( message = "{clientRequest.identDoc.validation.notNull}" )
    @Valid()
    private IdentDocRequest identDoc;
    
    @NotNull( message = "{clientRequest.arrest.validation.notNull}" )
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
    
}
