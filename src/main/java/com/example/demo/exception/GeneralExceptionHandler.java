package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleError(Exception ex) {
        return new ResponseEntity<>(new ExceptionResponse("Backend Error", ex.toString(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
