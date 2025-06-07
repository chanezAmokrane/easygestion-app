package com.crud.crud_springboot.Exception;
import org.springframework.http.HttpStatus;

public class ProductException

{
    private final String message;
    private final HttpStatus status;



    public ProductException(String message, HttpStatus status) {
        this.message = message;

        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }


}
