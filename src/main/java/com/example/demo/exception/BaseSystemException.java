package com.example.demo.exception;

import java.util.Map;

public class BaseSystemException extends Exception {

    private CustomExceptionStatus status;
    private Map<String, Object> data;

    public BaseSystemException(String message, CustomExceptionStatus status) {
        super(message);
        this.status = status;
    }

    public BaseSystemException(String message, CustomExceptionStatus status, Map<String, Object> data) {
        super(message);
        this.status = status;
        this.data = data;
    }

    public CustomExceptionStatus getStatus() {
        return status;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
