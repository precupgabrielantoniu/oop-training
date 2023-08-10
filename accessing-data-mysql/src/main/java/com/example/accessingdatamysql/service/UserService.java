package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.BookDTO;
import com.example.accessingdatamysql.dto.CreateUserDTO;
import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.Set;

public interface UserService {
    CreateUserDTO saveUser(CreateUserDTO userDTO);
    Iterable<DisplayUserDTO> findAllUsers();
    DisplayUserDTO deleteUser(Integer id) throws NoUserWithIdException;
    DisplayUserDTO getUserById(Integer id) throws Exception;
    CreateUserDTO updateUser(Integer id, CreateUserDTO newCreateUserDTO) throws Exception;
    DisplayUserDTO patchUser(Integer id, JsonPatch jsonPatch) throws NoUserWithIdException, JsonPatchException, JsonProcessingException;
    Set<BookDTO> getBooks(Integer id) throws NoUserWithIdException;
}
