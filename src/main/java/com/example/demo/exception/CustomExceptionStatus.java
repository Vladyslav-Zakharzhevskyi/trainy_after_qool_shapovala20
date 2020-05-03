package com.example.demo.exception;

public enum CustomExceptionStatus {
    EMAIL_TOKEN_NOT_FOUND(450, "Email token has not been found"),
    EMAIL_TOKEN_HAS_EXPIRED(452, "Used token has been expired"),
    EMAIL_HAS_NOT_CONFIRMED(455, "User has not confirmed his email yet.");

    private int code;
    private String description;

    CustomExceptionStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
