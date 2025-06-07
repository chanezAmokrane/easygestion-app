package com.crud.crud_springboot.Exception;

public class InvalidOrderException extends RuntimeException{
    public InvalidOrderException() {}
    public InvalidOrderException(String message) {
        super(message);
    }
    public InvalidOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
