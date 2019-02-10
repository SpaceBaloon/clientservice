package com.clientservice.client;

import com.clientservice.exceptions.InternalDataException;
import com.clientservice.misc.CertificateType;
import com.clientservice.misc.IdentDoc;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author BelkinSergei
 */
@Service
public class ClientService {
    
    @Autowired
    private ClientRepository repository;
    
    public Client findUniqueOneCaseInsensitive( String firstName, String lastName,
            CertificateType docType, String numberSeries ) {
        return repository.findUniqueOneCaseInsensitive(firstName, lastName, docType, numberSeries);
    }
    
    public Client saveClientFromRequest( Client client, IdentDoc identDoc ) {
        if( client == null || identDoc == null )
            throw new InternalDataException( this.getClass().getName() + ": parameters can't be null." );
        Client newClient = null;
        List<Client> clientList = repository.findByLastNameAndFirstNameAndIdentDocDocTypeAndIdentDocNumberSeries(
                client.getLastName(), client.getFirstName(), 
                identDoc.getDocType(), identDoc.getNumberSeries()
        );
        if( 1 < clientList.size() ) 
            throw new InternalDataException( "Requested client is not unique." );
        else if( !clientList.isEmpty() )
            newClient = repository.findById( clientList.get( 0 ).getId() )
                    .orElseThrow( () -> new InternalDataException( "Can't find client with id=" 
                            + clientList.get( 0 ).getId() ) );
        else {
            newClient = new Client();
            newClient.setFirstName( client.getFirstName() );
            newClient.setLastName( client.getLastName() );
            newClient.setIdentDoc( identDoc );
            newClient = repository.save( newClient );
        }
        return newClient;            
    }
    
}
