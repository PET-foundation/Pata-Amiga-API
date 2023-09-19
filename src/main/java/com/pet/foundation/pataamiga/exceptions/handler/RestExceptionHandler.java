package com.pet.foundation.pataamiga.exceptions.handler;

import com.pet.foundation.pataamiga.exceptions.EmailAlreadyExists;
import com.pet.foundation.pataamiga.exceptions.UserNotFoundException;
import com.pet.foundation.pataamiga.exceptions.patterns.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<ExceptionDetails> handlerBadRequestException(EmailAlreadyExists bre) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionDetails.builder()
                .title("Conflict Exception, check the documentation")
                .status(409)
                .details(bre.getMessage())
                .timestamp(System.currentTimeMillis())
                .developerMessage(bre.getClass().getName())
                .build());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerUserNotFoundException(UserNotFoundException bre) {
        return ResponseEntity.badRequest().body(ExceptionDetails.builder()
                .title("Not found Exception, check the documentation")
                .status(400)
                .details(bre.getMessage())
                .timestamp(System.currentTimeMillis())
                .developerMessage(bre.getClass().getName())
                .build());
    }

}
