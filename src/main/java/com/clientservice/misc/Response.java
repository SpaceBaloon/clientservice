package com.clientservice.misc;

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
    private ResultCode code;
    private String message;

    public long getArestId() {
        return arestId;
    }

    public void setArestId(long arestId) {
        this.arestId = arestId;
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response() {
    }

    public Response(long arestId, ResultCode code, String message) {
        this.arestId = arestId;
        this.code = code;
        this.message = message;
    }
    
}
