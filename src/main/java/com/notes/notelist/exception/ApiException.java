package com.notes.notelist.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ApiException {
    private final LocalDateTime timestamp;
    private final HttpStatus status;
    private final String message;

    public ApiException(String message, LocalDateTime timestamp, HttpStatus status) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }
}
