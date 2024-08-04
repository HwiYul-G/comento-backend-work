package com.demo.comentoStatistic.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ExternalAPIException extends RuntimeException {

    private final HttpStatus status;
    private final String responseBody;

    public ExternalAPIException(String message, HttpStatus status, String responseBody) {
        super(message);
        this.status = status;
        this.responseBody = responseBody;
    }

}
