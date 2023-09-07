package com.example.accessingdatamysql.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product Not Found")
public class NoProductWithIdException extends Exception {
    public NoProductWithIdException(String message) {
        super(message);
    }
}
