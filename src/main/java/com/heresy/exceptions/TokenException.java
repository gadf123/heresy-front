package com.heresy.exceptions;

public class TokenException extends RuntimeException{
    private String thisMessage;
    private String message;

    public TokenException(String message, String thisMessage){
        super(message);
        this.thisMessage = thisMessage;
        this.message = message;
    }

    public String getThisMessage() {
        return thisMessage;
    }

    public void setThisMessage(String thisMessage) {
        this.thisMessage = thisMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
