package com.clientservice.arrest;

/**
 *
 * @author BelkinSergei
 */
public enum ArestStatus {

    ACTIVE( "Active" ), PERFORMED( "Performed" ), CANCELED( "Canceled" );

    private String name;
        
    private ArestStatus( String name ) {
        this.name = name;        
    }

    @Override
    public String toString() {
        return name;
    }
    
}
