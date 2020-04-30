package com.example.demo.response;

public class BaseResponseEntity {

    private String title;
    private int status;
    private Object data;

    public BaseResponseEntity(String title, int status, Object data) {
        this.title = title;
        this.status = status;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public int getStatus() {
        return status;
    }

    public Object getData() {
        return data;
    }
}
