package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.BookDTO;
import com.example.accessingdatamysql.dto.CreateUserDTO;
import com.example.accessingdatamysql.dto.DisplayUserDTO;
import com.example.accessingdatamysql.entity.Book;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.repository.UserRepository;
import com.example.accessingdatamysql.transformers.BookTransformer;
import com.example.accessingdatamysql.transformers.CreateUserTransformer;
import com.example.accessingdatamysql.transformers.DisplayUserTransformer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DisplayUserTransformer displayUserTransformer;

    @Autowired
    private BookTransformer bookTransformer;

    @Autowired
    private CreateUserTransformer createUserTransformer;

    @Override
    public CreateUserDTO saveUser(CreateUserDTO userDTO){
        User user = createUserTransformer.fromDTO(userDTO);
        User savedUser = userRepository.save(user);
        return createUserTransformer.fromEntity(savedUser);
    }

    @Override
    public Iterable<DisplayUserDTO> findAllUsers(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(displayUserTransformer::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public DisplayUserDTO deleteUser(Integer id) throws NoUserWithIdException {
        Optional<User> userToDelete = userRepository.findById(id);
        User foundUser = userToDelete.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        userRepository.delete(foundUser);
        return displayUserTransformer.fromEntity(foundUser);
    }

    @Override
    public DisplayUserDTO getUserById(Integer id) throws Exception {
        Optional<User> foundOptionalUser = userRepository.findById(id);
        User foundUser = foundOptionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        return displayUserTransformer.fromEntity(foundUser);
    }

    @Override
    public CreateUserDTO updateUser(Integer id, CreateUserDTO newCreateUsedDTO) throws Exception {
        //Check notNull annotation from Spring/lombok
        Optional<User> foundOptionalUser = userRepository.findById(id);
        User foundUser = foundOptionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        foundUser.setName(newCreateUsedDTO.getName());
        foundUser.setEmail(newCreateUsedDTO.getEmail());
        foundUser.setPassword(newCreateUsedDTO.getPassword());
        User updatedUser = userRepository.save(foundUser);
        return createUserTransformer.fromEntity(updatedUser);
    }

    @Override
    public DisplayUserDTO patchUser(Integer id, JsonPatch jsonPatch) throws NoUserWithIdException, JsonPatchException, JsonProcessingException {
        Optional<User> foundOptionalUser = userRepository.findById(id);
        User foundUser = foundOptionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        User userPatched = applyPatchToUser(foundUser, jsonPatch);
        User updatedUser = userRepository.save(userPatched);
        return displayUserTransformer.fromEntity(updatedUser);
    }

    private User applyPatchToUser(User user, JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = jsonPatch.apply(objectMapper.convertValue(user, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }

    @Override
    public Set<BookDTO> getBooks(Integer id) throws NoUserWithIdException{
        Optional<User> foundOptionalUser = userRepository.findById(id);
        User foundUser = foundOptionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        return foundUser.getBooks().stream().map(bookTransformer::fromEntity).collect(Collectors.toSet());
    }
}
