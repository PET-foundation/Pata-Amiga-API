package com.pet.foundation.pataamiga.service;

import com.pet.foundation.pataamiga.controller.responses.LoginResponse;
import com.pet.foundation.pataamiga.controller.responses.RegisterResponse;
import com.pet.foundation.pataamiga.domain.user.dto.LoginDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;

public interface AuthService {
    LoginResponse login(LoginDTO loginDTO);

    RegisterResponse register(UserCreateDTO userCreateDTO);
}
