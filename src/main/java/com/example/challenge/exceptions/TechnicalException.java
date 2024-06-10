package com.example.challenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Technical exception occurred")
public class TechnicalException extends RuntimeException{

    public TechnicalException(String message) {
        super(message);
    }
}
