package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {

    private String title;
    private String description;
    private Integer status;

    public ExceptionResponse(String title, String description, Integer status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStatus() {
        return status;
    }
}
