package com.example.accessingdatamysql.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User Not Found")
public class NoBookWithIdException extends Exception {
    public NoBookWithIdException(String message) {
        super(message);
    }
}
