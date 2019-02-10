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
public class IdentDocRequestTest {
    
    @Autowired
    Validator validator;
    
    public IdentDocRequestTest() {
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
        new IdentDocRequest( 21, "", LocalDate.MAX ),
        new IdentDocRequest( 21, "   ", LocalDate.MAX ),
        new IdentDocRequest( null, null, LocalDate.MAX )
    };
    
    @Test
    public void testBadValidation() {
        for( IdentDocRequest e : badDocs ) {
            Set<ConstraintViolation<IdentDocRequest>> errors = validator.validate( e );
            assertFalse( errors.isEmpty() );
            System.out.println( "Errors count=" + errors.size() );
            errors.forEach((t) -> {
                System.out.println( t.getMessage() );
            });
        }
    }
    
    @Test
    public void testGoodValidation() {
        
    }
    
}
