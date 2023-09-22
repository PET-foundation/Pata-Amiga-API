package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.controller.responses.LoginResponse;
import com.pet.foundation.pataamiga.controller.responses.RegisterResponse;
import com.pet.foundation.pataamiga.domain.user.dto.LoginDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.service.AuthService;
import com.pet.foundation.pataamiga.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        return null;
    }

    @Override
    public RegisterResponse register(UserCreateDTO userCreateDTO) {
        String userCreatedUuid = userService.createUser(userCreateDTO);
        return new RegisterResponse(userCreatedUuid);
    }
}
