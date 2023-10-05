package com.example.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
public class LogDTO implements Serializable {

    @JsonProperty("message")
    String message;

    @JsonProperty("date")
    Date date;
}
