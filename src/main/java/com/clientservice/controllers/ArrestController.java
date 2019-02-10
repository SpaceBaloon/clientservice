package com.clientservice.controllers;

import com.clientservice.arrest.Arrest;
import com.clientservice.arrest.ArrestService;
import com.clientservice.exceptions.InternalDataException;
import com.clientservice.client.Client;
import com.clientservice.client.ClientRequestConverter;
import com.clientservice.misc.Response;
import com.clientservice.requestAPI.ClientRequest;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    
    private final ClientRequestConverter clientConverter;
    private final ArrestService arrestService;

    public ArrestController(ClientRequestConverter clientConverter, ArrestService arrestService) {
        this.clientConverter = clientConverter;
        this.arrestService = arrestService;
    }
    
    @PostMapping( "/put" )
    public Response putArrest( @RequestBody @Valid ClientRequest request ) {
        Client client = clientConverter.convert(request);
        Arrest arrest = arrestService.saveFromRequest( request.getOrganCode(), client, request.getArrest() );
        return new Response( arrest.getId(), Response.ResultCode.SUCCESS, "" );
    }
    
    /**
     * Handle all exceptions in this servlet.
     */
    @ExceptionHandler
    @ResponseStatus( HttpStatus.BAD_REQUEST )
    public Response handleException( Exception ex ) {
        Response.ResultCode code = Response.ResultCode.ERROR;
        String message = ex.getMessage();
        if( ex instanceof InternalDataException ) {
            code = Response.ResultCode.INTERNAL_ERROR;
        }
        if( ex instanceof MethodArgumentNotValidException )
            message = handleValidationExceptions( (MethodArgumentNotValidException) ex);
        return new Response( 0, code, message );
    }
    
    private String handleValidationExceptions( MethodArgumentNotValidException ex ) {
        final BindingResult errors = ex.getBindingResult();
        StringBuilder builder = new StringBuilder();
        builder.append("Validation failed with errors count=").append(errors.getErrorCount()).append(": ");
        for( ObjectError er : errors.getAllErrors() ) {
            builder.append( er.getDefaultMessage() ).append(", ");
        }
        return builder.toString();
    }

}
