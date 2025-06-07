package com.crud.crud_springboot.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


        @ExceptionHandler(ProductNotFoundException.class)
        public ResponseEntity<ProductException> handleProductNotFound(ProductNotFoundException ex) {
            ProductException error = new ProductException(ex.getMessage(), HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<OrderException> handleOrderNotFound(OrderNotFoundException ex) {
        OrderException error = new OrderException(ex.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ProductException> handleInvalidProduct(InvalidProductException ex) {
        ProductException error = new ProductException(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<OrderException> handleInvalidProduct(InvalidOrderException ex) {
        OrderException error = new OrderException(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(StockInsufficientException.class)
    public ResponseEntity<OrderException> handleStockInsufficientException(StockInsufficientException ex) {
        OrderException error = new OrderException(ex.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<ProductException> handleDuplicateProductException(DuplicateProductException ex) {
        ProductException error = new ProductException(ex.getMessage(), HttpStatus.CONFLICT);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}

