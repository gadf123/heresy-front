package com.heresy.controller;

import com.heresy.ExceptionResponse.ExceptionResponseHandler;
import com.heresy.exceptions.TokenException;
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
    public void defaultExceptionHandler(){

    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<ExceptionResponseHandler> TokenExceptionResponse(TokenException tokenException){
        ExceptionResponseHandler exceptionResponseHandler = new ExceptionResponseHandler();
        exceptionResponseHandler.setMessage1(tokenException.getMessage());
        exceptionResponseHandler.setMessage2(tokenException.getThisMessage());
        return new ResponseEntity(exceptionResponseHandler, HttpStatus.FORBIDDEN);
    }

}
