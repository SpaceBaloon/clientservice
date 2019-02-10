package com.clientservice.client;

import com.clientservice.misc.CertificateType;
import com.clientservice.misc.IdentDoc;
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
public class ClientServiceTest {
    
    @Autowired
    private ClientService service;
    @Autowired
    private ClientRepository repository;
    
    public ClientServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        assertNotNull( service );
        assertNotNull( repository );
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testRepositoryFindUniqueClient() {
        System.out.format("%100s%n", "testRepositoryFindUniqueClient");
        final Client[] clients = { 
            new Client( "petr", "petrov", new IdentDoc(CertificateType.PASSPORT, "123123 12 12") ),
            new Client( "PETR", "PetrOv", new IdentDoc(CertificateType.PASSPORT, "123123 12 12") ),
            new Client( "IVAN", "IVANOV", new IdentDoc(CertificateType.FOREIGN_PASSPORT, "321321 12") ),
            new Client( "ivan", "IVANOv", new IdentDoc(CertificateType.FOREIGN_PASSPORT, "321321 12") ),
        };
        
        for( Client client : clients ) {
            System.out.format("Input %s%n", client);
            Client actualClient = repository.findUniqueOneCaseInsensitive( client.getFirstName(), client.getLastName(),
                    client.getIdentDoc().getDocType(), client.getIdentDoc().getNumberSeries()
            );
            System.out.format("Output %s%n", actualClient);
            assertNotNull(  actualClient );
        }
        
    }
    
}
