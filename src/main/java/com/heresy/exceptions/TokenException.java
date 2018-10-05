package com.heresy.exceptions;

public class TokenException extends RuntimeException{
    private String mainMessage;
    private String subMessage;

    public TokenException(String mainMessage, String subMessage){
        super(mainMessage);
        this.mainMessage = mainMessage;
        this.subMessage = subMessage;
        super.printStackTrace();
    }

    public String getMainMessage() {
        return mainMessage;
    }

    /*public void setMessage(String message) {
        this.message = message;
    }*/

    public String getSubMessage() {
        return subMessage;
    }

    /*public void setThisMessage(String thisMessage) {
        this.thisMessage = thisMessage;
    }*/


}
