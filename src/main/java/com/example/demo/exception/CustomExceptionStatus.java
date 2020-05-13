package com.example.demo.exception;

public enum CustomExceptionStatus {
    GENERAL_EXCEPTION(500, "Some internal server error"),
    EMAIL_TOKEN_NOT_FOUND(450, "Email token has not been found"),
    EMAIL_TOKEN_HAS_EXPIRED(452, "Used token has been expired"),
    EMAIL_HAS_NOT_CONFIRMED(455, "User has not confirmed his email yet."),
    USER_NAME_NOT_FOUND(321, "User name not found"),
    BAD_CREDENTIALS(322, "Wrong Password");

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
