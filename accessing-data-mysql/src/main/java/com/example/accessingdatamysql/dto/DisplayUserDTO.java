package com.example.accessingdatamysql.dto;

import com.example.accessingdatamysql.entity.Address;
import com.example.accessingdatamysql.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DisplayUserDTO implements Serializable { //Data transfer object

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private AddressDTO addressDTO;
}
