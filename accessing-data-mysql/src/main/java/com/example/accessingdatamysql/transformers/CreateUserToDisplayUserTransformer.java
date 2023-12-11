package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.CreateUserDTO;
import com.example.accessingdatamysql.dto.DisplayUserDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateUserToDisplayUserTransformer implements DTOTransformer<CreateUserDTO, DisplayUserDTO>{

    public DisplayUserDTO transform(CreateUserDTO createUserDTO){
        DisplayUserDTO displayUserDTO = new DisplayUserDTO();
        displayUserDTO.setId(createUserDTO.getId());
        displayUserDTO.setName(createUserDTO.getName());
        displayUserDTO.setEmail(createUserDTO.getEmail());
        displayUserDTO.setAddressDTO(createUserDTO.getAddressDTO());
        return displayUserDTO;
    }
}
