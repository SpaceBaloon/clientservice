package com.clientservice;

import com.clientservice.controllers.ArrestController;
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
public class ClientserviceApplicationIT {
    
    @Autowired
    private ArrestController controller;
    
    public ClientserviceApplicationIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        assertNotNull( controller );
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testMain() {
        
    }
    
}
