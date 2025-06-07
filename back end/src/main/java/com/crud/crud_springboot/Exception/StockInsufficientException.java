package com.crud.crud_springboot.Exception;

public class StockInsufficientException extends RuntimeException {

    public StockInsufficientException(String message) {
        super(message);
    }
    public StockInsufficientException(String message, Throwable cause) {
        super(message, cause);
    }


}
