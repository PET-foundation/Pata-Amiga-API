package com.pet.foundation.pataamiga.service.impl;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.utils.UserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class TokenServiceImplTest {

    @InjectMocks
    private TokenServiceImpl tokenService;

    @Mock
    private User user;

    private final String secret = "secret";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(tokenService, "secret", secret);

        BDDMockito.when(user.getEmail())
                .thenReturn(UserCreator.returnValidUser().getEmail());
    }

    @Test
    @DisplayName("generateToken returns token when successful")
    void generateToken_ReturnsToken_WhenSuccessful() {
        String token = tokenService.generateToken(user);

        assertNotNull(token);
    }

    @Test
    @DisplayName("generateToken throws JWTCreationException when unsuccessful")
    void generateToken_ThrowsJWTCreationException_WhenUnsuccessful() {
        BDDMockito.when(user.getEmail())
                .thenThrow(new JWTCreationException("Error while generating token", new RuntimeException()));

        assertThrows(JWTCreationException.class, () -> tokenService.generateToken(user));
    }

    @Test
    @DisplayName("validateToken returns email when successful")
    void validateToken_ReturnsEmail_WhenSuccessful() {
        String token = tokenService.generateToken(user);

        String email = tokenService.validateToken(token);

        assertEquals(user.getEmail(), email);
    }

    @Test
    @DisplayName("validateToken returns empty string when unsuccessful")
    void validateToken_should_ReturnsAEmptyString_When_Unsuccessful() {
        String invalidToken = "invalid token";

        String subject = tokenService.validateToken(invalidToken);

        assertEquals("", subject);
    }

}