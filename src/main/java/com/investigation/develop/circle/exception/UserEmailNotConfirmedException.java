package com.investigation.develop.circle.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserEmailNotConfirmedException extends UsernameNotFoundException {

    private Code code;

    public UserEmailNotConfirmedException(String msg, Code code) {
        super(msg);
        this.code = code;
    }

    public Code getCode() {
        return code;
    }
}
