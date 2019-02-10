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
public class ArrestRequestTest {
    
    @Autowired
    private Validator validator;
    
    public ArrestRequestTest() {
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
    
    ArrestRequest[] goodArrests = {
        new ArrestRequest(LocalDate.MAX, "#12-1212", "On purpose", Long.MAX_VALUE, "", 1 ),
        new ArrestRequest(LocalDate.MAX, "# 12-1212", "On purpose", Long.MAX_VALUE, "12 12 123123", 2 ),
        new ArrestRequest(LocalDate.MAX, "# 12-12-12", "On purpose", Long.MAX_VALUE, "12 123123", 3 )
    };
    ArrestRequest[] badArrests = {
        new ArrestRequest(null, "#12 34", "On purpose", Long.MAX_VALUE, "", 1 ),
        new ArrestRequest(null, "<12 34", "On purpose", Long.MAX_VALUE, "", 1 ),
        new ArrestRequest(LocalDate.MAX, "#12-12  12@", "On purpose", null, "12 12 343434", 0 ),
        new ArrestRequest(LocalDate.MAX, "#12-12-1 /", "On purpose", Long.MAX_VALUE, null, 3 )
    };
    
    @Test
    public void testGoodValidation() {
    }
    
    @Test
    public void testBadValidation() {
        int i=0;
        for( ArrestRequest e : badArrests ) {
            Set<ConstraintViolation<ArrestRequest>> errors = validator.validate( e );
            assertFalse( errors.isEmpty() );
            System.out.println( ++i + ". Errors count=" + errors.size() );
            errors.forEach((t) -> {
                System.out.println( t.getMessage() );
            });
        }
    }
}
