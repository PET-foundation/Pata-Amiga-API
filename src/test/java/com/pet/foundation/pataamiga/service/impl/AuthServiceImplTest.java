package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.controller.responses.LoginResponse;
import com.pet.foundation.pataamiga.controller.responses.RegisterResponse;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.domain.user.dto.LoginDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.service.TokenService;
import com.pet.foundation.pataamiga.service.UserService;
import com.pet.foundation.pataamiga.utils.AuthRequestCreator;
import com.pet.foundation.pataamiga.utils.CreateUserDTOCreator;
import com.pet.foundation.pataamiga.utils.UserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AuthServiceImplTest {

    @InjectMocks
    private AuthServiceImpl authService;

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(userService.createUser(any(UserCreateDTO.class)))
                .thenReturn(UserCreator.returnValidUser().getUuid());
    }

    @Test
    @DisplayName("Should make login with successfully and return a token")
    void should_MakeLogin_When_Successfully_And_ReturnAToken() {
        String expectedToken = "expectedToken";

        LoginDTO userLogin = AuthRequestCreator.returnValidLoginDTO();

        Authentication authMock = mock(Authentication.class);

        User userAuthenticated = UserCreator.returnValidUser();

        when(authMock.getPrincipal()).thenReturn(userAuthenticated);
        when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authMock);
        when(tokenService.generateToken(any(User.class))).thenReturn(expectedToken);

        LoginResponse userAfterLogin = authService.login(userLogin);

        assertNotNull(userAfterLogin);
        assertEquals(expectedToken, userAfterLogin.token());
        assertDoesNotThrow(() -> authService.login(userLogin));
    }

    @Test
    @DisplayName("Should throw an exception when login with invalid credentials")
    void should_ThrowAnException_When_LoginWithInvalidCredentials() {
        LoginDTO userLogin = AuthRequestCreator.returnValidLoginDTO();

        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenThrow(new RuntimeException("Bad credentials"));

        assertThrows(RuntimeException.class, () -> authService.login(userLogin));
    }

    @Test
    @DisplayName("Should make register with successfully and return a uuid")
    void should_MakeRegister_When_Successfully_And_ReturnAUuid() {
        String expectedUuid = UserCreator.returnValidUser().getUuid();

        UserCreateDTO userCreateDTO = CreateUserDTOCreator.returnValidUserCreateDTO();

        RegisterResponse userAfterRegister = authService.register(userCreateDTO);

        assertNotNull(userAfterRegister);
        assertEquals(expectedUuid, userAfterRegister.uuid());
        assertDoesNotThrow(() -> authService.register(userCreateDTO));
    }

    @Test
    @DisplayName("Should throw an exception when register with invalid credentials")
    void should_ThrowAnException_When_RegisterWithInvalidCredentials() {
        UserCreateDTO userCreateDTO = CreateUserDTOCreator.returnValidUserCreateDTO();

        when(userService.createUser(any(UserCreateDTO.class)))
                .thenThrow(new IllegalArgumentException("Invalid credentials"));

        assertThrows(IllegalArgumentException.class, () -> authService.register(userCreateDTO));
    }
}