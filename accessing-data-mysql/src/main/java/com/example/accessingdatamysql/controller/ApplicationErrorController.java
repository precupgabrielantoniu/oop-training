package com.example.accessingdatamysql.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class ApplicationErrorController implements ErrorController  {

    @RequestMapping("/my-error")
    public String handleError() {
        //do something like logging

        return "error";
    }
}
