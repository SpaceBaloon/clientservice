package com.clientservice.arrest;

import com.clientservice.client.Client;
import com.clientservice.client.ClientRepository;
import com.clientservice.exceptions.InternalDataException;
import com.clientservice.misc.CertificateType;
import com.clientservice.requestAPI.ArrestRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
public class ArrestServiceTest {
    
    @Autowired
    ArrestService arrestService;
    
    @Autowired
    ArrestRepository arrestRepository;
    
    @Autowired
    ClientRepository clientRepository;
    
    public ArrestServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        assertNotNull( arrestService );
        assertNotNull( arrestRepository );
        assertNotNull( clientRepository );
    }
    
    @After
    public void tearDown() {
    }

//    @Test
    public void testSaveFromRequest() {
        Client client = clientRepository.findUniqueCaseDetailed( "ivan", "ivanov",
                CertificateType.FOREIGN_PASSPORT, "123456 12");
        assertNotNull("Client is null.", client );
        assertEquals( Long.valueOf(76), client.getId() );
        //new arrest
        Arrest arrest = arrestService.saveFromRequest( 17, client, 
                new ArrestRequest(LocalDate.MAX, "123-45", null, 99L, "", 1) );
        
        assertTrue( client.getArrests().isEmpty() );
        System.out.println( "List client arrests: " + Arrays.toString( client.getArrests().toArray() ) );
        
        List<Arrest> arrests = arrestRepository.findAll();
        assertNotNull( arrests );
        assertFalse( arrests.isEmpty() );
        System.out.println( "ArrestRepository: " + Arrays.toString( arrests.toArray() ) );

        Arrest arrestNext = arrestService.saveFromRequest( 17, client, 
                new ArrestRequest(LocalDate.MAX, "123-45", null, 100L, "Next", 2) );
        assertNotNull( arrestNext );
        assertTrue( 1 == arrestNext.getId() );
        System.out.println( "Changed arrest: " + arrestNext );
        
        Client clientNext = clientRepository.findUniqueCaseDetailed( client.getFirstName(), client.getLastName(), 
                client.getIdentDoc().getDocType(), client.getIdentDoc().getNumberSeries() );
        assertNotNull( clientNext );
        assertFalse( clientNext.getArrests().isEmpty() );
        System.out.println( "List client arrests: " + Arrays.toString( clientNext.getArrests().toArray() ) );
    }
    
    @Test
    public void testNewArrest() {
        
        Client client = clientRepository.findUniqueOneCaseInsensitive(
                "petr", "petrov", CertificateType.PASSPORT, "123456 12 12");
        assertNotNull( "Client is null.", client );
        assertEquals( Long.valueOf(232), client.getId() );
        
        LocalDate date = LocalDate.now();
        String number = "123-45";
        
        Arrest arrest = arrestService.saveFromRequest( 17, client, 
                new ArrestRequest( date, number, null, 99L, "", 1) );
        assertNotNull( "Arrest is null", arrest );
        assertEquals( Long.valueOf( 1 ) , arrest.getId() );
        System.out.println( "New arrest: " + arrest );
        
        try {
            Arrest arrest1 = arrestService.saveFromRequest( 17, client, 
                    new ArrestRequest(date, number, null, 99L, "", 1) );
            assertNull( arrest1 );
        } catch( InternalDataException ex ) {
            System.out.println( "Exception was thrown: " + ex.getMessage() );
        }
        
        arrest = arrestService.saveFromRequest( 17, client, 
                new ArrestRequest(date, number, "PURPOSE", 999L, "", 2 ) );
        assertNotNull( arrest );
        assertEquals( Long.valueOf( 1 ) , arrest.getId() );
        assertEquals( "PURPOSE", arrest.getBasis() );
        assertEquals( BigDecimal.valueOf( 999 ), arrest.getAmount() );
        System.out.println( "Update arrest: " + arrest );
        
        arrest = arrestRepository.findById( 1L ).orElseGet( null );
        assertNotNull(arrest);
        System.out.println( "Find arrest: " + arrest );
        
        arrest = arrestService.saveFromRequest( 17, client, 
                new ArrestRequest(date, number, "PURPOSE", 0L, "", 2 ) );
        assertNotNull( arrest );
        assertEquals( BigDecimal.valueOf( 1 ) , arrest.getId() );
        assertEquals( "newPURPOSE", arrest.getBasis() );
        assertEquals( Long.valueOf( 0 ), arrest.getAmount() );
        assertEquals( ArestStatus.CANCELED , arrest.getStatus() );
        System.out.println( "Cancel arrest: " + arrest );
        
        arrest = arrestRepository.findById( 1L ).orElseGet( null );
        assertNotNull(arrest);
        System.out.println( "Find arrest: " + arrest );
        
        try {
            Arrest arrest1 = arrestService.saveFromRequest( 17, client, 
                    new ArrestRequest(date, number, null, 99L, "", 1) );
            assertNull( arrest1 );
        } catch( InternalDataException ex ) {
            System.out.println( "Exception was thrown: " + ex.getMessage() );
        }
        
        arrest = arrestService.saveFromRequest( 17, client, 
                new ArrestRequest(date, number, "", 111L, "", 2 ) );
        assertNotNull( arrest );
        assertEquals( Long.valueOf( 1 ) , arrest.getId() );
        assertEquals( null, arrest.getBasis() );
        assertEquals( BigDecimal.valueOf( 111 ), arrest.getAmount() );
        assertEquals( ArestStatus.ACTIVE , arrest.getStatus() );
        System.out.println( "Activate arrest: " + arrest );
        
        arrest = arrestService.saveFromRequest( 17, client, 
                new ArrestRequest(date, number, null, 0L, "", 3 ) );
        assertNotNull( arrest );
        assertEquals( ArestStatus.CANCELED , arrest.getStatus() );
        System.out.println( "Cancel arrest: " + arrest );
    }
    
}
