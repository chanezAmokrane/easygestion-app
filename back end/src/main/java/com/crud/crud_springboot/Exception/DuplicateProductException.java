package com.crud.crud_springboot.Exception;

public class DuplicateProductException extends RuntimeException{

    public DuplicateProductException(String message){
        super(message);
    }
    public DuplicateProductException(String message, Throwable cause){
        super(message, cause);
    }
}
