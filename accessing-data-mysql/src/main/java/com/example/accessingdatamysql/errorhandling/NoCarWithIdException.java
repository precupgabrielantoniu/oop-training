package com.example.accessingdatamysql.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Car Not Found")
public class NoCarWithIdException extends Exception {
    public NoCarWithIdException(String message) {
        super(message);
    }
}