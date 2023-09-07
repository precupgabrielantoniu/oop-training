package com.example.accessingdatamysql.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.SERVICE_UNAVAILABLE, reason = "Car Not Found")
public class NoCarStockException extends Exception {
    public NoCarStockException(String message) {
        super(message);
    }
}
