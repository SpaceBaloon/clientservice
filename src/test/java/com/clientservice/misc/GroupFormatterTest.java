package com.clientservice.misc;

import java.util.Queue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author BelkinSergei
 */
public class GroupFormatterTest {
    
    public GroupFormatterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testParseText() {
        final String pattern = "NNN*%asN S S *-S=S";
        final String text = "1234-01)10";
        GroupFormatter frmter = GroupFormatter.getForPattern( pattern, text );
        frmter.parseText();
        assertQueue( frmter.table.get( 'N' ) );
        assertQueue( frmter.table.get( 'S' ) );
    }
    
    void assertQueue( Queue<Character> q ) {
        assertNotNull( q );
        assertFalse( q.isEmpty() );
        assertTrue( q.size() == 4 );
        System.out.println( q );
    }
    
    final String[] patterns = { "NNNNNN SS SS", "NNNNNN SS", "NNNNNN-SS", "NNNNNN.SS SS" };
    final String[] toPattern = { "SS SS NNNNNN", "SS NNNNNN", "SS-NNNNNN", "SS SS NNNNNN"};
    final String text = "121212 3434";
    
    @Test
    public void testToPattern() {
        int i = 0;
        for( String pattern : patterns ) {
            System.out.format("%d. pattern=%s text=%s conversion=", ++i, pattern, text );
            GroupFormatter formatter = GroupFormatter.getForPattern( pattern, text );
            System.out.println( formatter.convertToPattern(toPattern[i-1]) );
        }
    }
    
}
