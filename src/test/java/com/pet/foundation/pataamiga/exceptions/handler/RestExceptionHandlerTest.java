package com.pet.foundation.pataamiga.exceptions.handler;

import com.pet.foundation.pataamiga.exceptions.EmailAlreadyExists;
import com.pet.foundation.pataamiga.exceptions.PostNotFoundException;
import com.pet.foundation.pataamiga.exceptions.UserNotFoundException;
import com.pet.foundation.pataamiga.exceptions.patterns.ExceptionDetails;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @Test
    @DisplayName("handlerBadRequestException returns a response entity with the correct status code and body")
    public void should_HandlerBadRequestException_Return_AResponseEntity_With_TheCorrectStatusBody() {
        EmailAlreadyExists exception = new EmailAlreadyExists("Email already exists");
        ResponseEntity<ExceptionDetails> response = restExceptionHandler.handlerBadRequestException(exception);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Conflict Exception, check the documentation", response.getBody().getTitle());
        assertEquals(409, response.getBody().getStatus());
        assertEquals("Email already exists", response.getBody().getDetails());
    }

    @Test
    @DisplayName("handlerUserNotFoundException returns a response entity with the correct status code and body")
    public void should_HandlerUserNotFoundException_Return_AResponseEntity_With_TheCorrectStatusBody() {
        UserNotFoundException exception = new UserNotFoundException("User not found");
        ResponseEntity<ExceptionDetails> response = restExceptionHandler.handlerUserNotFoundException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Not found Exception, check the documentation", response.getBody().getTitle());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("User not found", response.getBody().getDetails());
    }

    @Test
    @DisplayName("handlerPostNotFoundException returns a response entity with the correct status code and body")
    public void should_HandlerPostNotFoundException_Return_AResponseEntity_With_TheCorrectStatusBody() {
        PostNotFoundException exception = new PostNotFoundException("Post not found");
        ResponseEntity<ExceptionDetails> response = restExceptionHandler.handlerUserNotFoundException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Not found Exception, check the documentation", response.getBody().getTitle());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Post not found", response.getBody().getDetails());
    }

    @Test
    @DisplayName("handlerIllegalArgumentException returns a response entity with the correct status code and body")
    public void should_HandlerIllegalArgumentException_Return_AResponseEntity_With_TheCorrectStatusBody() {
        IllegalArgumentException exception = new IllegalArgumentException("Illegal argument");
        ResponseEntity<ExceptionDetails> response = restExceptionHandler.handlerIllegalArgumentException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Arguments invalids Exception, check the documentation", response.getBody().getTitle());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Illegal argument", response.getBody().getDetails());
    }

}
