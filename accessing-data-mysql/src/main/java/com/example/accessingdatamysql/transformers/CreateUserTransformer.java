package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.CreateUserDTO;
import com.example.accessingdatamysql.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserTransformer implements Transformer<User, CreateUserDTO> {

    @Autowired
    private AddressTransformer addressTransformer;

    public CreateUserDTO fromEntity(User user){
        CreateUserDTO userDTO = new CreateUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAddressDTO(addressTransformer.fromEntity(user.getAddress()));
        return userDTO;
    }

    public User fromDTO(CreateUserDTO userDTO){
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAddress(addressTransformer.fromDTO(userDTO.getAddressDTO()));
        return user;
    }
}
