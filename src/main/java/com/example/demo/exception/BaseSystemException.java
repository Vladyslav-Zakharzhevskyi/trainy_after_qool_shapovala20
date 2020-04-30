package com.example.demo.exception;

public class BaseSystemException extends Exception {

    private String message;
    private CustomExceptionStatus status;

    public BaseSystemException(String message, CustomExceptionStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public CustomExceptionStatus getStatus() {
        return status;
    }
}
