package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.controller.responses.LoginResponse;
import com.pet.foundation.pataamiga.controller.responses.RegisterResponse;
import com.pet.foundation.pataamiga.domain.user.dto.LoginDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.service.AuthService;
import com.pet.foundation.pataamiga.service.UserService;
import com.pet.foundation.pataamiga.utils.AuthRequestCreator;
import com.pet.foundation.pataamiga.utils.AuthResponseCreator;
import com.pet.foundation.pataamiga.utils.CreateUserDTOCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        LoginResponse authServiceLoginReturn = AuthResponseCreator.returnValidLoginResponse();

        BDDMockito.when(authService.login(ArgumentMatchers.any(LoginDTO.class)))
                .thenReturn(authServiceLoginReturn);

        String userServiceCreateUserReturn = AuthResponseCreator.returnValidLoginResponse().token();

        BDDMockito.when(userService.createUser(ArgumentMatchers.any(UserCreateDTO.class)))
                .thenReturn(userServiceCreateUserReturn);

        RegisterResponse authServiceRegisterReturn = AuthResponseCreator.returnValidRegisterResponse();

        BDDMockito.when(authService.register(ArgumentMatchers.any(UserCreateDTO.class)))
                .thenReturn(authServiceRegisterReturn);
    }


    @Test
    @DisplayName("Login returns login response when successful")
    void login_ReturnsLoginResponse_WhenSuccessful() {
        String expectedToken = AuthResponseCreator.returnValidLoginResponse().token();

        LoginDTO loginDTO = AuthRequestCreator.returnValidLoginDTO();

        String actualToken = Objects.requireNonNull(authController.login(loginDTO).getBody()).token();

        assertEquals(expectedToken, actualToken);
    }

    @Test
    @DisplayName("should register an user and returns uuid when successful")
    void createUser_ReturnsUser_WhenSuccessful() {
        String uuidExpected = AuthResponseCreator.returnValidRegisterResponse().uuid();

        RegisterResponse userCreated = authController
                .register(CreateUserDTOCreator.returnValidUserCreateDTO()).getBody();

        assertNotNull(userCreated.uuid());
        assertEquals(uuidExpected, userCreated.uuid());
    }
}