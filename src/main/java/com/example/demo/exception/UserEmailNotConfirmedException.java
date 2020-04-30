package com.example.demo.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserEmailNotConfirmedException extends UsernameNotFoundException {

    private CustomExceptionStatus status;

    public UserEmailNotConfirmedException(String msg, CustomExceptionStatus status) {
        super(msg);
        this.status = status;
    }

    public CustomExceptionStatus getStatus() {
        return status;
    }
}
