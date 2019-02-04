package com.clientservice.misc;

/**
 * CertificateType stores internal pattern and name info of certificates.
 * Used internally in code to determine type of certificate.
 * In DB mapped as 0 or 1.
 *
 * @author BelkinSergei
 */
public enum CertificateType {
    
    PASSPORT( 1, "Passport RF", "NNNNNN SS SS" ), 
    FOREIGN_PASSPORT( 2, "Foreign passport RF", "NNNNNN SS");
    
    private int id;
    private String name;
    private String format;

    private CertificateType( int id, String name, String format ) {
        this.id = id;
        this.name = name;
        this.format = format;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String toString() {
        return name;
    }
        
}
