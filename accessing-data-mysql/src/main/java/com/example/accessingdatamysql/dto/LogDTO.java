package com.example.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogDTO implements Serializable {

    @JsonProperty("message")
    String message;

    @JsonProperty("date")
    Date date;
}
