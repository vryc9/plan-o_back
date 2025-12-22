package com.example.planeo_back.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExpenseExceptionHandler.class)
    public ResponseEntity<String> expenseExceptionHandler(ExpenseExceptionHandler expenseExceptionHandler) {
        return new ResponseEntity<>(expenseExceptionHandler.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
