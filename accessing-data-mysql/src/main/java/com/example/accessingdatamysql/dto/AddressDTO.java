package com.example.accessingdatamysql.dto;

import com.example.accessingdatamysql.entity.Address;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class AddressDTO implements Serializable {
    @JsonProperty("number")
    private Integer number;

    @JsonProperty("street")
    private String street;

    @JsonProperty("city")
    private String city;

    @JsonProperty("county")
    private String county;

    public static AddressDTO fromEntity(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.number = address.getNumber();
        addressDTO.street = address.getStreet();
        addressDTO.city = address.getCity();
        addressDTO.county = address.getCounty();
        return addressDTO;
    }

    public static Address fromDTO(AddressDTO addressDTO){
        return Address.builder()
                .number(addressDTO.number)
                .street(addressDTO.street)
                .city(addressDTO.city)
                .county(addressDTO.county)
                .build();
    }
}
