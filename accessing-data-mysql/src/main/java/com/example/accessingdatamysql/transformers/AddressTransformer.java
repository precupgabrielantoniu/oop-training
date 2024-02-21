package com.example.accessingdatamysql.transformers;

import com.example.accessingdatamysql.dto.AddressDTO;
import com.example.accessingdatamysql.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressTransformer implements Transformer<Address, AddressDTO> {
    public AddressDTO fromEntity(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setCounty(address.getCounty());
        return addressDTO;
    }

    public Address fromDTO(AddressDTO addressDTO){
        return Address.builder()
                .id(addressDTO.getId())
                .number(addressDTO.getNumber())
                .street(addressDTO.getStreet())
                .city(addressDTO.getCity())
                .county(addressDTO.getCounty())
                .build();
    }
}
