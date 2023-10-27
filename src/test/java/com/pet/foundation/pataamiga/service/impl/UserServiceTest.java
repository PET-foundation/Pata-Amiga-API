package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.exceptions.EmailAlreadyExists;
import com.pet.foundation.pataamiga.exceptions.UserNotFoundException;
import com.pet.foundation.pataamiga.repositories.UserRepository;
import com.pet.foundation.pataamiga.utils.CreateUserDTOCreator;
import com.pet.foundation.pataamiga.utils.UserCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {

        BDDMockito.when(userRepository.findByUuid(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(UserCreator.returnValidUser()));

        BDDMockito.when(userRepository.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(UserCreator.returnValidUser());

        BDDMockito.when(userRepository.save(ArgumentMatchers.any(User.class)))
                .thenReturn(UserCreator.returnValidUser());
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("getUserByUuid returns user when successful")
    void getUserByUuid_ReturnsUser_WhenSuccessful() {
        String uuidExpected = UserCreator.returnValidUser().getUuid();

        User user = userService.getUserByUuid(uuidExpected);

        assertNotNull(user);
        assertEquals(uuidExpected, user.getUuid());
    }

    @Test
    @DisplayName("getUserByEmail returns user when successful")
    void getUserByEmail_ReturnsUser_WhenSuccessful() {
        String emailExpected = UserCreator.returnValidUser().getEmail();

        User user = userService.getUserByEmail(emailExpected);

        assertNotNull(user);
        assertEquals(emailExpected, user.getEmail());
    }

    @Test
    @DisplayName("getUserByEmail returns null when user is not found")
    void getUserByEmail_ReturnsNull_WhenUserIsNotFound() {
        BDDMockito.when(userRepository.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(null);

        User user = userService.getUserByEmail("email");

        assertNull(user);
    }

    @Test
    @DisplayName("createUser returns user when successful")
    void createUser_ReturnsUser_WhenSuccessful() {

        BDDMockito.when(userRepository.findByEmail(ArgumentMatchers.anyString()))
                .thenReturn(null);

        String uuidExpected = UserCreator.returnValidUser().getUuid();

        String uuid = userService.createUser(CreateUserDTOCreator.returnValidUserCreateDTO());

        assertNotNull(uuid);
        assertEquals(uuidExpected, uuid);
    }

    @Test
    @DisplayName("Should throw an exception when user email already exists")
    void should_Throw_An_Exception_When_UserEmail_Already_Exists() {

        assertThrows(EmailAlreadyExists.class, () -> {
            userService.createUser(CreateUserDTOCreator.returnValidUserCreateDTO());
        });
    }

    @Test
    @DisplayName("updateUserByUuid updates user when successful")
    void updateUserByUuid_UpdatesUser_WhenSuccessful() {
        String uuidExpected = UserCreator.returnValidUser().getUuid();

        assertDoesNotThrow(() ->
                userService.updateUserByUuid(uuidExpected, CreateUserDTOCreator.returnValidUserUpdateDTO()));
    }

    @Test
    @DisplayName("updateUserByUuid should throw an UserNotFoundException when user is not found")
    void updateUserByUuid_ShouldThrowAnUserNotFoundException_WhenUserIsNotFound() {
        BDDMockito.when(userRepository.findByUuid(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.updateUserByUuid("uuid", CreateUserDTOCreator.returnValidUserUpdateDTO()));
    }

    @Test
    @DisplayName("deleteUserByUuid deletes user when successful")
    void deleteUserByUuid_DeletesUser_WhenSuccessful() {
        String uuidExpected = UserCreator.returnValidUser().getUuid();

        assertDoesNotThrow(() ->
                userService.deleteUserByUuid(uuidExpected));
    }

    @Test
    @DisplayName("deleteUserByUuid should throw an UserNotFoundException when user is not found")
    void deleteUserByUuid_ShouldThrowAnUserNotFoundException_WhenUserIsNotFound() {
        BDDMockito.when(userRepository.findByUuid(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () ->
                userService.deleteUserByUuid("uuid"));
    }

}