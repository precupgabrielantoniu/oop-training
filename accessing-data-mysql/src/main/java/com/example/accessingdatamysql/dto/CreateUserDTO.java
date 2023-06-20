package com.example.accessingdatamysql.dto;

import com.example.accessingdatamysql.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class CreateUserDTO implements Serializable { //Data transfer object

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    public static CreateUserDTO fromEntity(User user){
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.name = user.getName();
        userDTO.email = user.getEmail();
        userDTO.password = user.getPassword();
        return userDTO;
    }

    public static User fromDTO(CreateUserDTO userDTO){
        User user = new User();
        user.setName(userDTO.name);
        user.setEmail(userDTO.email);
        user.setPassword(userDTO.password);
        return user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
