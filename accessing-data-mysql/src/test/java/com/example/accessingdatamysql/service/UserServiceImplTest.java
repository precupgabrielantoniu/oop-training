package com.example.accessingdatamysql.service;

import com.example.accessingdatamysql.dto.AddressDTO;
import com.example.accessingdatamysql.dto.CreateUserDTO;
import com.example.accessingdatamysql.entity.Address;
import com.example.accessingdatamysql.entity.User;
import com.example.accessingdatamysql.errorhandling.NoUserWithIdException;
import com.example.accessingdatamysql.repository.UserRepository;
import com.example.accessingdatamysql.transformers.AddressTransformer;
import com.example.accessingdatamysql.transformers.CreateUserTransformer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    private final static Integer USER_ID = 25;

    private final static Integer ADDRESS_ID = 27;

    private final static Integer OLD_ADDRESS_ID = 15;

    private final static String EMPTY_STRING = "";

    private final static String ERROR_MESSAGE = "No user found with this id.";


    @Mock
    private UserRepository userRepository;

    @Mock
    private CreateUserTransformer createUserTransformer;

    @Mock
    private AddressTransformer addressTransformer;

    @InjectMocks
    private UserServiceImpl userServiceUnderTest = new UserServiceImpl();

    private static final Address oldAddress = Address.builder()
            .id(ADDRESS_ID)
            .city("New York City")
            .county("New York")
            .number(45)
            .street("1st")
            .build();

    private static final AddressDTO oldAddressDTO = AddressDTO.builder()
            .id(ADDRESS_ID)
            .city("New York City")
            .county("New York")
            .number(45)
            .street("1st")
            .build();

    private static final Address updatedAddress = Address.builder()
            .id(OLD_ADDRESS_ID)
            .city("LA")
            .street("21 Jump Street")
            .number(56)
            .county("California")
            .build();

    private static final AddressDTO updatedAddressDTO = AddressDTO.builder()
            .id(OLD_ADDRESS_ID)
            .city("LA")
            .street("21 Jump Street")
            .number(56)
            .county("California")
            .build();

    private static final User existingUser = User.builder()
            .id(USER_ID)
            .name("Marian")
            .email("email@yahoo.com")
            .address(oldAddress)
            .password("hardpassword")
            .build();



    private static final CreateUserDTO userWithAddressToUpdateDTO = CreateUserDTO.builder()
            .id(USER_ID)
            .name("Marian")
            .email("email@yahoo.com")
            .addressDTO(updatedAddressDTO)
            .build();

    private static final CreateUserDTO expectedUserWithUpdatedAddressDTO = CreateUserDTO.builder()
            .id(USER_ID)
            .name("Marian")
            .email("email@yahoo.com")
            .password("hardpassword")
            .addressDTO(updatedAddressDTO)
            .build();

    private static final CreateUserDTO userWithNameToUpdateDTO = CreateUserDTO.builder()
            .id(USER_ID)
            .name("Marian123")
            .email("email@yahoo.com")
            .addressDTO(oldAddressDTO)
            .password("hardpassword")
            .build();

    private static final CreateUserDTO expectedUserWithUpdatedNameDTO = CreateUserDTO.builder()
            .id(USER_ID)
            .name("Marian")
            .email("email@yahoo.com")
            .addressDTO(oldAddressDTO)
            .password("hardpassword")
            .build();

    private static final User databaseUpdatedUserForAddress = User.builder()
            .id(USER_ID)
            .name("Marian")
            .email("email@yahoo.com")
            .password("hardpassword")
            .address(updatedAddress)
            .build();

    private static final CreateUserDTO databaseUpdatedUserDTOForAddress = CreateUserDTO.builder()
            .id(USER_ID)
            .name("Marian")
            .email("email@yahoo.com")
            .password("hardpassword")
            .addressDTO(updatedAddressDTO)
            .build();

    private static final User databaseUpdatedUserForName = User.builder()
            .id(USER_ID)
            .name("Marian")
            .email("email@yahoo.com")
            .address(oldAddress)
            .password("hardpassword")
            .build();

    private static final CreateUserDTO databaseUpdatedUserDTOForName = CreateUserDTO.builder()
            .id(USER_ID)
            .name("Marian")
            .email("email@yahoo.com")
            .password("hardpassword")
            .addressDTO(oldAddressDTO)
            .build();

    private static final CreateUserDTO userWithNullValuesToUpdateDTO = CreateUserDTO.builder()
            .password(EMPTY_STRING)
            .name(EMPTY_STRING)
            .email(EMPTY_STRING)
            .build();

    private static final CreateUserDTO expectedUserWithUpdatedNullValuesDTO = CreateUserDTO.builder()
            .password("hardpassword")
            .name("Marian")
            .email(EMPTY_STRING)
            .build();

    private static final User databaseUpdatedUserForNullValues = User.builder()
            .password("hardpassword")
            .name("Marian")
            .email(EMPTY_STRING)
            .build();

    private static final CreateUserDTO databaseUpdatedUserDTOForNullValues = CreateUserDTO.builder()
            .password("hardpassword")
            .name("Marian")
            .email(EMPTY_STRING)
            .build();

    private static Stream<Arguments> provideUsersForSuccesfulUpdate() {
        return Stream.of(
                Arguments.of(existingUser, databaseUpdatedUserForAddress, databaseUpdatedUserDTOForAddress,
                        userWithAddressToUpdateDTO, expectedUserWithUpdatedAddressDTO, updatedAddress),
                Arguments.of(existingUser, databaseUpdatedUserForName, databaseUpdatedUserDTOForName,
                        userWithNameToUpdateDTO, expectedUserWithUpdatedNameDTO, oldAddress),
                Arguments.of(existingUser, databaseUpdatedUserForNullValues, databaseUpdatedUserDTOForNullValues,
                        userWithNullValuesToUpdateDTO, expectedUserWithUpdatedNullValuesDTO, null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideUsersForSuccesfulUpdate")
    public void testUpdateUserSucceeded(User existingUser, User databaseUpdatedUser,
                                        CreateUserDTO databaseUpdatedUserDTO,
                                        CreateUserDTO userToBeUpdatedDTO,
                                        CreateUserDTO expectedCreateUserDTO,
                                        Address updatedAddress) throws Exception {

        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(existingUser));
        when(addressTransformer.fromDTO(userToBeUpdatedDTO.getAddressDTO())).thenReturn(updatedAddress);
        when(userRepository.save(existingUser)).thenReturn(databaseUpdatedUser);
        when(createUserTransformer.fromEntity(databaseUpdatedUser)).thenReturn(databaseUpdatedUserDTO);
        CreateUserDTO actualUpdatedUserDTO = userServiceUnderTest.updateUser(USER_ID, userToBeUpdatedDTO);
        Assertions.assertThat(actualUpdatedUserDTO).isEqualToComparingFieldByField(expectedCreateUserDTO);
        //assertThat(actualUpdatedUserDTO).isEqualToComparingFieldByFieldRecursively(expectedCreateUserDTO);
    }

    @Test
    public void testUpdateUserFailed(){
        AddressDTO newAddressDTO = AddressDTO.builder()
                .county("county")
                .number(54)
                .street("street")
                .city("city")
                .id(ADDRESS_ID)
                .build();
        CreateUserDTO userToBeUpdatedDTO = CreateUserDTO.builder()
                .password("password")
                .name("Name")
                .email("email@email.com")
                .addressDTO(newAddressDTO)
                .build();
        Optional<User> emptyUser = Optional.empty();

        when(userRepository.findById(USER_ID)).thenReturn(emptyUser);

        Exception exception = assertThrows(NoUserWithIdException.class, () -> {
            userServiceUnderTest.updateUser(USER_ID, userToBeUpdatedDTO);
        });
        assertEquals(ERROR_MESSAGE, exception.getMessage());
        verify(userRepository, times(0)).save(any());
    }

    //This test was updated with parameters - testUpdateUserSucceeded
    @Test
    public void testUpdateUserAddressSucceeded() throws Exception {
        AddressDTO newAddressDTO = AddressDTO.builder()
                .id(OLD_ADDRESS_ID)
                .city("LA")
                .street("21 Jump Street")
                .number(56)
                .county("California")
                .build();

        Address newAddress = Address.builder()
                .id(OLD_ADDRESS_ID)
                .city("LA")
                .street("21 Jump Street")
                .number(56)
                .county("California")
                .build();
        Address oldAddress = Address.builder()
                .id(ADDRESS_ID)
                .city("New York City")
                .county("New York")
                .number(45)
                .street("1st")
                .build();

        CreateUserDTO userToBeUpdatedDTO = CreateUserDTO.builder()
                .id(USER_ID)
                .name("Marian")
                .email("email@yahoo.com")
                .addressDTO(newAddressDTO)
                .build();

        User existingUser = User.builder()
                .id(USER_ID)
                .name("Marian")
                .email("email@yahoo.com")
                .address(oldAddress)
                .password("hardpassword")
                .build();

        User updatedUser = User.builder()
                .id(USER_ID)
                .name("Marian")
                .email("email@yahoo.com")
                .password("hardpassword")
                .address(newAddress)
                .build();

        CreateUserDTO updatedUserDTO = CreateUserDTO.builder()
                .id(USER_ID)
                .name("Marian")
                .email("email@yahoo.com")
                .password("hardpassword")
                .addressDTO(newAddressDTO)
                .build();

        CreateUserDTO expectedCreateUserDTO = CreateUserDTO.builder()
                .id(USER_ID)
                .name("Marian")
                .email("email@yahoo.com")
                .password("hardpassword")
                .addressDTO(newAddressDTO)
                .build();


        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(existingUser));
        when(addressTransformer.fromDTO(userToBeUpdatedDTO.getAddressDTO())).thenReturn(newAddress);
        when(userRepository.save(existingUser)).thenReturn(updatedUser);
        when(createUserTransformer.fromEntity(updatedUser)).thenReturn(updatedUserDTO);
        CreateUserDTO actualUpdatedUserDTO = userServiceUnderTest.updateUser(USER_ID, userToBeUpdatedDTO);
        Assertions.assertThat(actualUpdatedUserDTO).isEqualToComparingFieldByField(expectedCreateUserDTO);
        //assertThat(actualUpdatedUserDTO).isEqualToComparingFieldByFieldRecursively(expectedCreateUserDTO);
    }
}
