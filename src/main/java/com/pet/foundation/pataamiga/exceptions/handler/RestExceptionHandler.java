package com.pet.foundation.pataamiga.exceptions.handler;

import com.pet.foundation.pataamiga.exceptions.EmailAlreadyExists;
import com.pet.foundation.pataamiga.exceptions.UserNotFound;
import com.pet.foundation.pataamiga.exceptions.patterns.ExceptionDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<ExceptionDetails> handlerBadRequestException(EmailAlreadyExists bre) {
        return ResponseEntity.badRequest().body(ExceptionDetails.builder()
                .title("Bad Request Exception, check the documentation")
                .status(400)
                .details(bre.getMessage())
                .timestamp(System.currentTimeMillis())
                .developerMessage(bre.getClass().getName())
                .build());
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ExceptionDetails> handlerBadRequestException(UserNotFound bre) {
        return ResponseEntity.badRequest().body(ExceptionDetails.builder()
                .title("Not found Exception, check the documentation")
                .status(400)
                .details(bre.getMessage())
                .timestamp(System.currentTimeMillis())
                .developerMessage(bre.getClass().getName())
                .build());
    }

}