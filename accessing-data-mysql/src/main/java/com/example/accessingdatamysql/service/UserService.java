package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CreateUserDTO;
import com.example.accessingdatamysql.dto.DisplayBookDTO;
import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.dto.ProductDTO;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import javax.naming.directory.InvalidAttributesException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;


public interface UserService {
    CreateUserDTO saveUser(CreateUserDTO userDTO) throws InvalidAttributesException, SQLIntegrityConstraintViolationException;
    Iterable<DisplayUserDTO> findAllUsers();
    DisplayUserDTO deleteUser(Integer id) throws NoUserWithIdException;
    DisplayUserDTO getUserById(Integer id) throws Exception;
    CreateUserDTO updateUser(Integer id, CreateUserDTO newCreateUserDTO) throws Exception;
    DisplayUserDTO patchUser(Integer id, JsonPatch jsonPatch) throws NoUserWithIdException, JsonPatchException, JsonProcessingException;
    Set<DisplayBookDTO> getBooks(Integer id) throws NoUserWithIdException;
    Set<ProductDTO> getProducts(Integer userId) throws NoUserWithIdException;

}
