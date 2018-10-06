package com.heresy.controller;

import com.heresy.ExceptionResponse.ExceptionResponseHandler;
import com.heresy.exceptions.TokenException;
import com.heresy.exceptions.UserServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @user park
 * @date 2018. 9. 27.
 **/

@RestControllerAdvice
public class ExceptionHandleController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseHandler> defaultExceptionHandler(Exception exception){
        ExceptionResponseHandler exceptionResponseHandler = new ExceptionResponseHandler();
        exceptionResponseHandler.setMainMessage(exception.getMessage());
        exception.printStackTrace();
        return new ResponseEntity(exceptionResponseHandler, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ExceptionResponseHandler> TokenExceptionResponse(TokenException exception){
        ExceptionResponseHandler exceptionResponseHandler = new ExceptionResponseHandler();
        exceptionResponseHandler.setMainMessage(exception.getMainMessage());
        exceptionResponseHandler.setSubMessage(exception.getSubMessage());
        return new ResponseEntity(exceptionResponseHandler, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserServiceException.class)
    public ResponseEntity<ExceptionResponseHandler> UserBindingExceptionResponse(UserServiceException exception){
        ExceptionResponseHandler exceptionResponseHandler = new ExceptionResponseHandler();
        exceptionResponseHandler.setMainMessage(exception.getMainMessage());
        exceptionResponseHandler.setSubMessage(exception.getSubMessage());
        return new ResponseEntity(exceptionResponseHandler, HttpStatus.BAD_REQUEST);
    }
}
