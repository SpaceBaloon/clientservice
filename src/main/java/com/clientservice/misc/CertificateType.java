package com.clientservice.misc;

/**
 * CertificateType stores internal pattern and name info of certificates.
 * Used internally in code to determine type of certificate.
 * In DB mapped as 0 or 1.
 *
 * @author BelkinSergei
 */
public enum CertificateType {
    
    PASSPORT( 1, "Passport RF", "NNNNNN SS SS", "^\\d{6} \\d{2} \\d{2}$" ), 
    FOREIGN_PASSPORT( 2, "Foreign passport RF", "NNNNNN SS", "^\\d{6} \\d{2}$");
    
    private final int id;
    private final String name;
    private final String conversionPattern;
    private final String matchPattern;

    private CertificateType( int id, String name, 
            String conversionPattern, String matchPattern ) {
        this.id = id;
        this.name = name;
        this.conversionPattern = conversionPattern;
        this.matchPattern = matchPattern;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getConversionPattern() {
        return conversionPattern;
    }

    public String getMatchPattern() {
        return matchPattern;
    }

    @Override
    public String toString() {
        return name;
    }
        
}
