package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.controller.responses.LoginResponse;
import com.pet.foundation.pataamiga.controller.responses.RegisterResponse;
import com.pet.foundation.pataamiga.domain.user.dto.LoginDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.service.AuthService;
import com.pet.foundation.pataamiga.swagger.annotatios.ConflictResponse;
import com.pet.foundation.pataamiga.swagger.annotatios.CreatedResponse;
import com.pet.foundation.pataamiga.swagger.annotatios.ForbiddenResponse;
import com.pet.foundation.pataamiga.swagger.annotatios.OkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "You can login with your name, email and password")
    @Tag(name = "auth")
    @OkResponse
    @ForbiddenResponse
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(authService.login(loginDTO));
    }


    @PostMapping("/register")
    @Operation(summary = "Register", description = "You can register with your name, email and password")
    @Tag(name = "auth")
    @CreatedResponse
    @ConflictResponse
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(userCreateDTO));
    }
}
