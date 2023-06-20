package com.example.accessingdatamysql.errorhandling;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User Not Found")
public class NoUserWithIdException extends Exception {
    public NoUserWithIdException(String message) {
        super(message);
    }
}
