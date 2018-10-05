package com.heresy.ExceptionResponse;

public class ExceptionResponseHandler {
    private String mainMessage;
    private String subMessage;

    public ExceptionResponseHandler(){

    }

    public String getMainMessage() {
        return mainMessage;
    }

    public void setMainMessage(String message1) {
        this.mainMessage = message1;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }
}
