package com.clientservice.client;

import com.clientservice.misc.CertificateType;
import com.clientservice.misc.IdentDoc;
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
public class ClientRepositoryTest {
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private Validator validator;
    
    public ClientRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        assertNotNull( clientRepository );
        assertNotNull( validator );
    }
    
    @After
    public void tearDown() {
    }
    
    IdentDoc[] badDocs = { 
        new IdentDoc(CertificateType.PASSPORT, " 123123 12 12"),
        new IdentDoc(CertificateType.PASSPORT, "123123 12 12 "),
        new IdentDoc(CertificateType.PASSPORT, "123123 12  12"),
        new IdentDoc(CertificateType.PASSPORT, "qweqwe 12 12"),
        new IdentDoc(CertificateType.PASSPORT, "12312312 12"),
        new IdentDoc(CertificateType.FOREIGN_PASSPORT, " 123123 12"),
        new IdentDoc(CertificateType.FOREIGN_PASSPORT, "123123 1 2"),
        new IdentDoc(CertificateType.FOREIGN_PASSPORT, "123123 12 ")
    };
    IdentDoc[] correctDocs = {
        new IdentDoc(CertificateType.PASSPORT, "123123 12 12"),
        new IdentDoc(CertificateType.FOREIGN_PASSPORT, "123123 12")
    };    
    
    @Test
    public void testPrePersist() {
        final String nameExpr = "^[A-Z]{1}[a-z]*$";
        Client[] clients = { new Client( "tb", "FamilyName", correctDocs[0] ),
            new Client( "New", "COMER", correctDocs[1] )
        };
        for( Client c : clients ) {
            Client result = clientRepository.save( c );
            assertNotNull( result );
            System.out.format("%s%n", result );
            assertTrue( result.getFirstName().matches( nameExpr ) );
            assertTrue( result.getLastName().matches( nameExpr ) );
        }
    }
    
//    @Test
    public void testIdentDocViolationViaValidate() {
        for( IdentDoc doc : badDocs ) {
            Client c = new Client( "First", "Second", doc );
            Set<ConstraintViolation<Client>> errors = validator.validate( c );
            assertFalse( errors.isEmpty() );
            errors.forEach( ( e ) -> {
                System.out.println( "Validation: " + e.getMessage() );
            } );
        }
    }
    
}
