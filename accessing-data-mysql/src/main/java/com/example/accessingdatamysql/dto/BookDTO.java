package com.example.accessingdatamysql.dto;

import com.example.accessingdatamysql.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class BookDTO implements Serializable {
    @JsonProperty("title")
    private String title;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("page_count")
    private Integer pageCount;

    private User owner;
}
