package com.clientservice.misc;

import java.time.LocalDate;

/**
 *
 * @author BelkinSergei
 */
public class Passport {
    
    private final int nnumber;
    private final int series;

    public Passport(int nnumber, int series) {
        this.nnumber = nnumber;
        this.series = series;
    }
    
    public static Passport fromString( String format, String value ) {
        throw new UnsupportedOperationException( "Not implemented yet." );
    }
    
}
