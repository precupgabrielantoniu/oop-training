package com.example.accessingdatamysql.dto;

import com.example.accessingdatamysql.entity.Address;
import com.example.accessingdatamysql.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class DisplayUserDTO implements Serializable { //Data transfer object

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private AddressDTO addressDTO;

    public static DisplayUserDTO fromEntity(User user){
        DisplayUserDTO userDTO = new DisplayUserDTO();
        userDTO.name = user.getName();
        userDTO.email = user.getEmail();
        userDTO.addressDTO = AddressDTO.fromEntity(user.getAddress());
        return userDTO;
    }

    public static User fromDTO(DisplayUserDTO userDTO){
        User user = new User();
        user.setName(userDTO.name);
        user.setEmail(userDTO.email);
        user.setAddress(AddressDTO.fromDTO(userDTO.addressDTO));
        return user;
    }

    public static DisplayUserDTO fromCreatedUserDTO(CreateUserDTO createUserDTO){
        DisplayUserDTO displayUserDTO = new DisplayUserDTO();
        displayUserDTO.name = createUserDTO.getName();
        displayUserDTO.email = createUserDTO.getEmail();
        displayUserDTO.addressDTO = createUserDTO.getAddressDTO();
        return displayUserDTO;
    }
}
