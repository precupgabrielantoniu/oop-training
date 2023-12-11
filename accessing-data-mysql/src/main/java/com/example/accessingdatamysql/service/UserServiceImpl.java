package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.*;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.repository.UserRepository;
import com.example.accessingdatamysql.transformers.BookTransformer;
import com.example.accessingdatamysql.transformers.CreateUserTransformer;
import com.example.accessingdatamysql.transformers.DisplayUserTransformer;
import com.example.accessingdatamysql.transformers.ProductTransformer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.directory.InvalidAttributesException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {
    private static final String LOG_MESSAGE = "User with name %s saved successfully";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private DisplayUserTransformer displayUserTransformer;

    @Autowired
    private BookTransformer bookTransformer;

    @Autowired
    private CreateUserTransformer createUserTransformer;

    @Autowired
    private ProductTransformer productTransformer;

    @Override
    @Transactional(rollbackFor = {InvalidAttributesException.class, SQLIntegrityConstraintViolationException.class}, noRollbackFor = ConstraintViolationException.class )
    public CreateUserDTO saveUser(CreateUserDTO userDTO) throws InvalidAttributesException, SQLIntegrityConstraintViolationException {
        User user = createUserTransformer.fromDTO(userDTO);
        LogDTO logDTO = new LogDTO();
        logDTO.setDate(Date.from(Instant.now()));
        logDTO.setMessage(String.format(LOG_MESSAGE, user.getName()));
        logService.saveLog(logDTO);
        if(!user.getName().matches("[a-zA-Z]+"))
            throw new SQLIntegrityConstraintViolationException(); // Checked Exception
        User savedUser = userRepository.save(user);
        if (!user.getEmail().contains("@"))
            throw new InvalidAttributesException(); // Checked Exception
        if (user.getPassword().length() < 8)
            throw new ConstraintViolationException("Invalid password length", null, "Password length"); // Unchecked Exception
        return createUserTransformer.fromEntity(savedUser);
    }

    @Override
    public Iterable<DisplayUserDTO> findAllUsers() {
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
        if(newCreateUsedDTO.getPassword() != null && !newCreateUsedDTO.getPassword().equals("")) {
            foundUser.setPassword(newCreateUsedDTO.getPassword());
        }
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
    public Set<BookDTO> getBooks(Integer id) throws NoUserWithIdException {
        Optional<User> foundOptionalUser = userRepository.findById(id);
        User foundUser = foundOptionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        return foundUser.getBooks().stream().map(bookTransformer::fromEntity).collect(Collectors.toSet());
    }

    @Override
    public Set<ProductDTO> getProducts(Integer userId) throws NoUserWithIdException {
        Optional<User> foundOptionalUser = userRepository.findById(userId);
        User foundUser = foundOptionalUser.orElseThrow(() -> new NoUserWithIdException("No user found with this id."));
        return foundUser.getProducts().stream().map(p -> productTransformer.fromEntity(p)).collect(Collectors.toSet());
    }
}
