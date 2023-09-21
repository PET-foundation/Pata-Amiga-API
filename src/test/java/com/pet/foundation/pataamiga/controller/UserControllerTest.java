package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.controller.responses.UserCreatedResponse;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.domain.user.dto.UserResponseDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserUpdateDTO;
import com.pet.foundation.pataamiga.exceptions.UserNotFoundException;
import com.pet.foundation.pataamiga.service.UserService;
import com.pet.foundation.pataamiga.utils.CreateUserDTOCreator;
import com.pet.foundation.pataamiga.utils.UserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(userService.getUserByUuid(ArgumentMatchers.anyString()))
                .thenReturn(UserCreator.returnValidUser());

        BDDMockito.when(userService.createUser(ArgumentMatchers.any()))
                .thenReturn(UserCreator.returnValidUser().getUuid());

        BDDMockito.doNothing()
                .when(userService)
                .updateUserByUuid(ArgumentMatchers.anyString(), ArgumentMatchers.any(UserUpdateDTO.class));

        BDDMockito.doNothing()
                .when(userService)
                .deleteUserByUuid(ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("getUserByUuid returns user when successful")
    void getUserByUuid_ReturnsUser_WhenSuccessful() {
        String uuidExpected = UserCreator.returnValidUser().getUuid();

        UserResponseDTO userFound = userController.getUserByUuid(uuidExpected).getBody();

        assertNotNull(userFound);
        assertEquals(uuidExpected, userFound.getUuid());
    }

    @Test
    @DisplayName("getUserByUuid should throw UserNotFoundException when user does not exists")
    void getUserByUuid_ThrowsUserNotFoundException_WhenUserDoesNotExists() {

        BDDMockito.when(userService.getUserByUuid(ArgumentMatchers.anyString()))
                .thenThrow(new UserNotFoundException("User not found"));

        assertThrows(UserNotFoundException.class, () -> userController.getUserByUuid("1"));
    }

    @Test
    @DisplayName("createUser returns user when successful")
    void createUser_ReturnsUser_WhenSuccessful() {
        String uuidExpected = UserCreator.returnValidUser().getUuid();

        UserCreatedResponse userCreated = userController
                .createUser(CreateUserDTOCreator.returnValidUserCreateDTO()).getBody();

        assertNotNull(userCreated);
        assertNotNull(userCreated.uuid());
        assertEquals(uuidExpected, userCreated.uuid());
    }

    @Test
    @DisplayName("updateUser returns user when successful")
    void updateUser_ReturnsUser_WhenSuccessful() {
        ResponseEntity<User> userResponseEntity = userController
                .updateUser(UserCreator.returnValidUser().getUuid(), CreateUserDTOCreator.returnValidUserUpdateDTO());

        assertDoesNotThrow(() -> userResponseEntity);

        assertEquals(userResponseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("deleteUser returns user when successful")
    void deleteUser_ReturnsUser_WhenSuccessful() {

        ResponseEntity<User> userResponseEntity = userController
                .deleteUser(UserCreator.returnValidUser().getUuid());

        assertDoesNotThrow(() -> userResponseEntity);

        assertEquals(userResponseEntity.getStatusCode(), HttpStatus.NO_CONTENT);
    }

}