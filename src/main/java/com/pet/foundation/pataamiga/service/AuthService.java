package com.pet.foundation.pataamiga.service;

import com.pet.foundation.pataamiga.controller.responses.LoginResponse;
import com.pet.foundation.pataamiga.controller.responses.RegisterResponse;
import com.pet.foundation.pataamiga.domain.user.dto.LoginDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface AuthService {
    LoginResponse login(LoginDTO loginDTO);

    LoginResponse loginWithGoogle(String token) throws Exception;

    RegisterResponse register(UserCreateDTO userCreateDTO);
}
