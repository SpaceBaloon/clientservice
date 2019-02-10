package com.clientservice.requestAPI;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
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
public class ClientRequestTest {
    
    @Autowired
    Validator validator;
    
    public ClientRequestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws UnsupportedEncodingException {
        assertNotNull( validator );
        System.setOut( new PrintStream( System.out, true, "UTF-8") );
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
    
    IdentDocRequest[] badDocs = {
        new IdentDocRequest( 0, "12 12 123123", LocalDate.MAX ),
        new IdentDocRequest( 21, " ", LocalDate.MAX ),
        new IdentDocRequest( 80, "12312312", LocalDate.MAX ),
        new IdentDocRequest( null, null, LocalDate.MAX )
    };
    
    ArrestRequest[] goodArrests = {
        new ArrestRequest(LocalDate.MAX, "#12-1212", "On purpose", Long.MAX_VALUE, "", 1 ),
        new ArrestRequest(LocalDate.MAX, "# 12-1212", "On purpose", Long.MAX_VALUE, "12 12 123123", 2 ),
        new ArrestRequest(LocalDate.MAX, "# 12-12-12", "On purpose", Long.MAX_VALUE, "12 123123", 3 )
    };
    ArrestRequest[] badArrests = {
        new ArrestRequest(LocalDate.MAX, "<12 34", "On purpose", Long.MAX_VALUE, "", 1 ),
        new ArrestRequest(LocalDate.MAX, "#12-1212", "On purpose", Long.MAX_VALUE, "12 12 343434", 2 ),
        new ArrestRequest(LocalDate.MAX, "#12-12-12", "On purpose", Long.MAX_VALUE, "12 12 343434", 3 )
    };
    
    ClientRequest[] goodClients = {
        new ClientRequest( "123", "lastName", "firstName", 39, goodDocsFNS[0], goodArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 39, goodDocsFNS[1], goodArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 17, goodDocsFSPP[0], goodArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 17, goodDocsFSPP[1], goodArrests[0] ),
        
        new ClientRequest( "123", "lastName", "firstName", 39, goodDocsFNS[0], goodArrests[1] ),
        new ClientRequest( "123", "lastName", "firstName", 39, goodDocsFNS[1], goodArrests[2] )
    };
    
    ClientRequest[] badClients = {
        new ClientRequest( "123", null, "firstName", 39, null, badArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 39, goodDocsFNS[0], null ),
        new ClientRequest( "123", "lastName", "firstName", 39, null, null ),
        
        new ClientRequest( "123", null, "firstName", 39, goodDocsFNS[0], badArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 39, goodDocsFNS[1], badArrests[1] ),
        new ClientRequest( "123", "lastName", "firstName", 17, goodDocsFSPP[0], badArrests[2] ),

        
        new ClientRequest( "123", "lastName", null, 39, badDocs[0], goodArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 17, badDocs[1], goodArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 17, badDocs[2], goodArrests[0] ),
        new ClientRequest( "123", "lastName", "firstName", 17, badDocs[3], goodArrests[0] )
    };
    
//    @Test
    public void testGoodValidation() {
        for( ClientRequest c : goodClients ) {
            Set<ConstraintViolation<ClientRequest>> errors = validator.validate( c );
            assertTrue( errors.isEmpty() );
        }
    }
    
    @Test
    public void testBadValidation() {
        int i = 0;
        for( ClientRequest c : badClients ) {
            Set<ConstraintViolation<ClientRequest>> errors = validator.validate( c );
            assertFalse( errors.isEmpty() );
            System.out.println( ++i + ". Errors count=" + errors.size() );
            errors.forEach( (t) -> { System.out.println( t.getMessage() );
            } );
        }
    }
    
    
}
