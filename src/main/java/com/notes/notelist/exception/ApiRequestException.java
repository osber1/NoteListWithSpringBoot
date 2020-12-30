package com.notes.notelist.exception;

import org.springframework.http.HttpStatus;

public abstract class ApiRequestException extends RuntimeException {
    public ApiRequestException(String message) {
        super(message);
    }

    public abstract HttpStatus getHttpStatus();
}
