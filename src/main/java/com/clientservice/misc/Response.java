package com.clientservice.misc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Transient;

/**
 * This for response.
 *
 * @author BelkinSergei
 */
public class Response {
    
    public static enum ResultCode {
        SUCCESS( 0 ), INTERNAL_ERROR( 3 ), ERROR( 5 );

        private final int code;
        
        private ResultCode( int code ) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
                
    }
    
    private long arestId;
    private int code;
    private String message;
    
    @JsonIgnore
    private ResultCode resultCode;

    public int getCode() {
        return code;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
        code = resultCode == null ? 0 : resultCode.getCode();
    }

    public long getArestId() {
        return arestId;
    }

    public void setArestId(long arestId) {
        this.arestId = arestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response() {
    }

    public Response(long arestId, ResultCode resultCode, String message) {
        this.arestId = arestId;
        this.message = message;
        this.code = ( resultCode == null ? 0 : resultCode.getCode() );
        this.resultCode = resultCode;
    }
    
}
