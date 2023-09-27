package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.controller.responses.LoginResponse;
import com.pet.foundation.pataamiga.controller.responses.RegisterResponse;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.domain.user.dto.LoginDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.service.AuthService;
import com.pet.foundation.pataamiga.service.TokenService;
import com.pet.foundation.pataamiga.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken userNamePassword =
                new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());

        Authentication auth = this.authenticationManager.authenticate(userNamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponse(token);
    }

    @Override
    public RegisterResponse register(UserCreateDTO userCreateDTO) {
        String userCreatedUuid = userService.createUser(userCreateDTO);
        return new RegisterResponse(userCreatedUuid);
    }
}
