package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DisplayUserTransformer implements Transformer<User, DisplayUserDTO> {

    @Autowired
    private AddressTransformer addressTransformer;

    public DisplayUserDTO fromEntity(User user){
        DisplayUserDTO userDTO = new DisplayUserDTO();
        userDTO.setId((user.getId()));
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddressDTO(addressTransformer.fromEntity(user.getAddress()));
        return userDTO;
    }

    public User fromDTO(DisplayUserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setAddress(addressTransformer.fromDTO(userDTO.getAddressDTO()));
        return user;
    }
}
