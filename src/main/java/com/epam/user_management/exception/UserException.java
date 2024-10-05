package com.epam.user_management.exception;

import org.springframework.http.HttpStatus;


public class UserException extends RuntimeException {
    private HttpStatus httpStatus;
    public UserException(HttpStatus httpStatus,String message){
        super(message);
        this.httpStatus=httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

