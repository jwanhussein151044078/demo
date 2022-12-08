package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(NotExistsException.class)
    public ResponseEntity<?> dataNotFoundExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails(new Date(),
                        exception.getMessage(),
                        request.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<?> dataAlreadyExistsExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails(new Date(),
                        exception.getMessage(),
                        request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> PassWordDoesNotMatchExceptionHandling(Exception exception, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails(new Date(),
                        exception.getMessage(),
                        request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }
}
