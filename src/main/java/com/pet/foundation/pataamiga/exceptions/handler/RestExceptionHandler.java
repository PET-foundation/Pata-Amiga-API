package com.pet.foundation.pataamiga.exceptions.handler;

import com.pet.foundation.pataamiga.exceptions.EmailAlreadyExists;
import com.pet.foundation.pataamiga.exceptions.PostNotFoundException;
import com.pet.foundation.pataamiga.exceptions.UserNotFoundException;
import com.pet.foundation.pataamiga.exceptions.patterns.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

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

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handlerUserNotFoundException(PostNotFoundException bre) {
        return ResponseEntity.badRequest().body(ExceptionDetails.builder()
                .title("Not found Exception, check the documentation")
                .status(400)
                .details(bre.getMessage())
                .timestamp(System.currentTimeMillis())
                .developerMessage(bre.getClass().getName())
                .build());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionDetails> handlerIllegalArgumentException(IllegalArgumentException bre) {
        return ResponseEntity.badRequest().body(ExceptionDetails.builder()
                .title("Arguments invalids Exception, check the documentation")
                .status(400)
                .details(bre.getMessage())
                .timestamp(System.currentTimeMillis())
                .developerMessage(bre.getClass().getName())
                .build());
    }
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ExceptionDetails> handlerNullPointerException(NullPointerException bre) {
        return ResponseEntity.badRequest().body(ExceptionDetails.builder()
                .title("Null Pointer Exception, check the documentation")
                .status(400)
                .details(bre.getMessage())
                .timestamp(System.currentTimeMillis())
                .developerMessage(bre.getClass().getName())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails> handlerMethodArgumentNotValidExceptionException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
        String fields = fieldError.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldMessage = fieldError.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(
                ExceptionDetails.builder()
                        .title("Bad Request Exception, Invalid Fields")
                        .status(400)
                        .details("Check the field(s) error")
                        .timestamp(System.currentTimeMillis())
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldMessage)
                        .build());
    }

}
