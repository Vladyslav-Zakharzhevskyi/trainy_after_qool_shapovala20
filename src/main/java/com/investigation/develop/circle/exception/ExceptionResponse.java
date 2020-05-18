package com.investigation.develop.circle.exception;


import java.util.Map;

public class ExceptionResponse {

    private String title;
    private String description;
    private Integer status;
    private Map<String, Object> data;

    public ExceptionResponse(String title, String description, Integer status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public ExceptionResponse(String title, String description, Integer status, Map<String, Object> data) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.data = data;
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

    public Map<String, Object> getData() {
        return data;
    }
}
