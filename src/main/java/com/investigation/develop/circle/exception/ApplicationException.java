package com.investigation.develop.circle.exception;

import java.util.Map;

public class ApplicationException extends RuntimeException {

    private Code code;
    private Map<String, Object> data;

    public ApplicationException(String message, Code code) {
        super(message);
        this.code = code;
    }

    public ApplicationException(String message, Code code, Map<String, Object> data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public Code getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }
}
