package com.crud.crud_springboot.Exception;

import org.springframework.http.HttpStatus;

public class OrderException {
    private final String message ;
    private final HttpStatus httpStatus ;


    public OrderException(String message,HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;

    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
    public String getMessage() {
        return message;
    }

}
