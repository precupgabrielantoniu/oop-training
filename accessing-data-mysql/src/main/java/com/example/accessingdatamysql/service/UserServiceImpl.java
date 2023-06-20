package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.CreateUserDTO;
import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CreateUserDTO saveUser(CreateUserDTO userDTO){
        User user = CreateUserDTO.fromDTO(userDTO);
        User savedUser = userRepository.save(user);
        return CreateUserDTO.fromEntity(savedUser);
    }

    @Override
    public Iterable<DisplayUserDTO> findAllUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(DisplayUserDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DisplayUserDTO deleteUser(Integer id) throws NoUserWithIdException {
        Optional<User> userToDelete = userRepository.findById(id);
        User foundUser = userToDelete.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        userRepository.delete(foundUser);
        return DisplayUserDTO.fromEntity(foundUser);
    }

    @Override
    public DisplayUserDTO getUserById(Integer id) throws Exception {
        Optional<User> foundOptionalUser = userRepository.findById(id);
        User foundUser = foundOptionalUser.orElseThrow(() -> new Exception("No user found with this id."));
        return DisplayUserDTO.fromEntity(foundUser);
    }


}
