package com.clientservice.controllers;

import com.clientservice.agency.Agency;
import com.clientservice.agency.AgencyService;
import com.clientservice.exceptions.InternalDataException;
import com.clientservice.client.Client;
import com.clientservice.client.ClientRepository;
import com.clientservice.misc.IdentDoc;
import com.clientservice.misc.Response;
import com.clientservice.client.ClientService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTlike web service. 
 * Single method ArrestController.putArrest receives application/json data.
 * Sending POST request with json data:
 * {
	"requestId": 1, "lastName": "Newcomer", "firstName": "Name", 
	"identDoc": { "type": 70, "numberSeries": "123123-1212", "issueDate": "22.12.1980" },
	"organCode": 17, 
	"arrest": { "date": "23.01.2001", "number": "#12.300", "basis": "for purpose", "amount": 12344,"operation": 1 }
    }
 *  will yield
 * {
    "arestId": 1,
    "code": "SUCCESS",
    "message": ""
    }
 * If there is no such client it will be created.
 * Client: id=1, lastName=Newcomer, firstName=Name, birthDate=null, birthPlace=null, 
 * identDoc=IdentDoc: docType=0, numberSeries=123123 12 12, issueDate=1980-12-22, arrests=[]
 * 
 * @author BelkinSergei
 */
@RestController
public class ArrestController {
    
    private final ClientService clientService;
    private final AgencyService agencyService;

    public ArrestController(ClientService clientService, AgencyService agencyService) {
        this.clientService = clientService;
        this.agencyService = agencyService;
    }
    
    @PostMapping( "/post" )
    public Response putArrest( @RequestBody @Valid Client client ) {
        /**
         * certificate validation.
         */
        //find agency, throw exception
        Agency agency = agencyService.findByCodeWithDetails( client.getOrganCode() );
        //corresond pattern
        if( !agencyService.isMatchPattern(agency, client.getIdentDoc() ) )
            throw new InternalDataException( "Pattern of certificate is not matched." );
        //convert certificate to internal format
        IdentDoc docToSave = agencyService.toInternalCertificate( 
                client.getIdentDoc(),
                agencyService.getCerificate(agency, client.getIdentDoc() ) 
        );
        //retrieve client
        Client newClient = clientService.saveClientFromRequest(client, docToSave);
        
        System.out.println( newClient.toString() );
        
        /**
         * TODO: arrest validation.
         */
        return new Response( newClient.getId(), Response.ResultCode.SUCCESS, "" );
    }
    
    /**
     * Handle all exceptions in this servlet.
     */
    @ExceptionHandler
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public Response handleException( Exception ex ) {
        /**
         * Logging section.
         */
        Response.ResultCode code = Response.ResultCode.ERROR;
        int id = 0;
        if( ex instanceof InternalDataException ) code = Response.ResultCode.INTERNAL_ERROR;
        return new Response( 0, code, ex.getMessage() );
    }
    
    /**
     * This is only for testing.
     */
    
    @GetMapping( "/test/agency")
    public Agency getAgency( @RequestParam( value = "id") int id ) {
        return agencyService.findByCodeWithDetails( id );
    }
    
    @GetMapping( "/test/agencies" )
    public List<Agency> getAllAgency() {
        return agencyService.getAllAgency();
    }

}
