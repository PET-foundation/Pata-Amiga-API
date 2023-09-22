package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.controller.responses.LoginResponse;
import com.pet.foundation.pataamiga.controller.responses.RegisterResponse;
import com.pet.foundation.pataamiga.domain.user.dto.LoginDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.exceptions.patterns.ExceptionDetails;
import com.pet.foundation.pataamiga.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pet.foundation.pataamiga.swagger.SwaggerResponses.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "You can login with your name, email and password")
    @Tag(name = "auth")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = RESPONSE_403,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "200", description = RESPONSE_200, useReturnTypeSchema = true)
    })
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }


    @PostMapping("/register")
    @Operation(summary = "Register", description = "You can register with your name, email and password")
    @Tag(name = "auth")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "409", description = RESPONSE_409,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionDetails.class))),
            @ApiResponse(responseCode = "201", description = RESPONSE_201, useReturnTypeSchema = true)
    })
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userCreateDTO));
    }
}
