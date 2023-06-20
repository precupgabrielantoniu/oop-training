package com.example.accessingdatamysql.dto;

import com.example.accessingdatamysql.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class DisplayUserDTO implements Serializable { //Data transfer object

    @JsonProperty("name")
    public String name;

    @JsonProperty("email")
    public String email;

    public static DisplayUserDTO fromEntity(User user){
        DisplayUserDTO userDTO = new DisplayUserDTO();
        userDTO.name = user.getName();
        userDTO.email = user.getEmail();
        return userDTO;
    }

    public static User fromDTO(DisplayUserDTO userDTO){
        User user = new User();
        user.setName(userDTO.name);
        user.setEmail(userDTO.email);
        return user;
    }

    public static DisplayUserDTO fromCreatedUserDTO(CreateUserDTO createUserDTO){
        DisplayUserDTO displayUserDTO = new DisplayUserDTO();
        displayUserDTO.name = createUserDTO.getName();
        displayUserDTO.email = createUserDTO.getEmail();
        return displayUserDTO;
    }
}
