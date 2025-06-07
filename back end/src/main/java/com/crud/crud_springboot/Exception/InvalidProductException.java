package com.crud.crud_springboot.Exception;

public class InvalidProductException extends RuntimeException {
public InvalidProductException(String message) {
    super(message);
}
public InvalidProductException(String message, Throwable cause) {
    super(message, cause);
}
}
