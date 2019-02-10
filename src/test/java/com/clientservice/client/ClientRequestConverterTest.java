package com.clientservice.client;

import com.clientservice.misc.IdentDoc;
import com.clientservice.requestAPI.ArrestRequest;
import com.clientservice.requestAPI.ClientRequest;
import com.clientservice.requestAPI.IdentDocRequest;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author BelkinSergei
 */
@RunWith( SpringRunner.class )
@SpringBootTest
public class ClientRequestConverterTest {
    
    @Autowired
    ClientRequestConverter converter;
    
    public ClientRequestConverterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        assertNotNull( converter );
    }
    
    @After
    public void tearDown() {
    }
        
    IdentDocRequest[] goodDocsFNS = { 
        new IdentDocRequest( 21, "12 12 123123", LocalDate.MAX ),
        new IdentDocRequest( 22, "12 123123", LocalDate.MAX )
    };
    
    IdentDocRequest[] goodDocsFSPP = { 
        new IdentDocRequest( 70, "123123-1212", LocalDate.MAX ),
        new IdentDocRequest( 80, "123123.12", LocalDate.MAX )
    };
        
    ArrestRequest[] goodArrests = {
        new ArrestRequest(LocalDate.MAX, "#12-1212", "On purpose", Long.MAX_VALUE, "", 1 ),
        new ArrestRequest(LocalDate.MAX, "# 12-1212", "On purpose", Long.MAX_VALUE, "12 12 123123", 2 ),
        new ArrestRequest(LocalDate.MAX, "# 12-12-12", "On purpose", Long.MAX_VALUE, "12 123123", 3 )
    };
    
    ClientRequest[] goodClients = {
        new ClientRequest( "123", "lastName", "firstName", 39, goodDocsFNS[0], goodArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 39, goodDocsFNS[1], goodArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 17, goodDocsFSPP[0], goodArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 17, goodDocsFSPP[1], goodArrests[0] ),        
        new ClientRequest( "123", "lastName", "firstName", 39, goodDocsFNS[0], goodArrests[1] ),
        new ClientRequest( "123", "lastName", "firstName", 39, goodDocsFNS[1], goodArrests[2] ),
        
        new ClientRequest( "1", "Popov", "Victor", 39, 
                new IdentDocRequest( 21, "12 12 123456", LocalDate.MAX ), goodArrests[2] ),
        new ClientRequest( "1", "IvanoV", "IvaN", 39, 
                new IdentDocRequest( 22, "12 123456", LocalDate.MAX ), goodArrests[2] ),
        new ClientRequest( "1", "popov", "victor", 17, 
                new IdentDocRequest( 70, "123456-1212", LocalDate.MAX ), goodArrests[2] ),
        new ClientRequest( "1", "IvanoV", "IvaN", 17, 
                new IdentDocRequest( 80, "123456.12", LocalDate.MAX ), goodArrests[2] )
    };
    
    @Test
    public void testConverter() {
        int i=0;
        for( ClientRequest r : goodClients ) {
            Client client = converter.convert( r );
            System.out.format( "%d. %s%n", i, client );
        }
    }
    
}
