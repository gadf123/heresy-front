package com.heresy.exceptions;

public class UserServiceException extends RuntimeException{
    private String mainMessage;
    private String subMessage;

    public UserServiceException() {

    }

    public UserServiceException(String type, String mainMessage, String subMessage) {
        super(type);
        this.mainMessage = mainMessage;
        this.subMessage = subMessage;
        super.printStackTrace();

    }

    public String getMainMessage() {
        return mainMessage;
    }

    public void setMainMessage(String mainMessage) {
        this.mainMessage = mainMessage;
    }

    public String getSubMessage() {
        return subMessage;
    }

    public void setSubMessage(String subMessage) {
        this.subMessage = subMessage;
    }
}
