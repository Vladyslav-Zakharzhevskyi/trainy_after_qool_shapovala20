package com.investigation.develop.circle.exception;

public enum Code {
    GENERAL_EXCEPTION(500, "Internal server error"),
    VALUE_MISS_MATCH(415, "Provided value has inconsistent state"),
    VALIDATION_EXCEPTION(405, "Validation exception has occured"),
    USER_IS_NOT_AUTHORIZED(403, "Current user isn't Authorized"),
    EMAIL_TOKEN_NOT_FOUND(450, "Email token has not been found"),
    EMAIL_TOKEN_HAS_EXPIRED(452, "Used token has been expired"),
    EMAIL_HAS_NOT_CONFIRMED(455, "User has not confirmed his email yet."),
    USER_NAME_NOT_FOUND(321, "User name not found"),
    BAD_CREDENTIALS(322, "Wrong Password");

    private int value;
    private String description;

    Code(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
