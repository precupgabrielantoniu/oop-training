package com.example.accessingdatamysql.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateUserDTO implements Serializable { //Data transfer object

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("address")
    private AddressDTO addressDTO;

    public static class Builder {

        private String name;

        private String email;

        private String password;

        private AddressDTO addressDTO;

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder email(String email){
            this.email = email;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder addressDTO(AddressDTO addressDTO){
            this.addressDTO = addressDTO;
            return this;
        }

        public CreateUserDTO build(){
            CreateUserDTO createUserDTO = new CreateUserDTO();
            createUserDTO.name = name;
            createUserDTO.email = email;
            createUserDTO.password = password;
            createUserDTO.addressDTO = addressDTO;
            return createUserDTO;
        }
    }
}
