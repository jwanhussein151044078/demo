package com.example.demo.exception;

public class NotExistsException extends RuntimeException {
    public NotExistsException() {}
    public NotExistsException(String message) {
        super(message);
    }
}