package com.example.planeo_back.application.exception;

public class ExpenseExceptionHandler extends RuntimeException {
    public ExpenseExceptionHandler(String message) {
        super(message);
    }
}
