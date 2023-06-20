package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CreateUserDTO;
import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;

public interface UserService {
    CreateUserDTO saveUser(CreateUserDTO userDTO);
    Iterable<DisplayUserDTO> findAllUsers();
    DisplayUserDTO deleteUser(Integer id) throws NoUserWithIdException;
    DisplayUserDTO getUserById(Integer id) throws Exception;
    CreateUserDTO updateUser(Integer id, CreateUserDTO newCreateUserDTO) throws Exception;
}
