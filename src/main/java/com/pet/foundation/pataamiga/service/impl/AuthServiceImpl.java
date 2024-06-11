package com.pet.foundation.pataamiga.service.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.pet.foundation.pataamiga.controller.responses.LoginResponse;
import com.pet.foundation.pataamiga.controller.responses.RegisterResponse;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.domain.user.dto.LoginDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.service.AuthService;
import com.pet.foundation.pataamiga.service.TokenService;
import com.pet.foundation.pataamiga.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @Value("${google.client.id}")
    private String GOOGLE_CLIENT_ID;

    @Override
    public LoginResponse login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken userNamePassword =
                new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password());

        Authentication auth = this.authenticationManager.authenticate(userNamePassword);
        String token = tokenService.generateToken((User) auth.getPrincipal());
        return new LoginResponse(token);
    }

    @Override
    public LoginResponse loginWithGoogle(String token) throws Exception {

        log.info("token: {}", token);
        GoogleIdTokenVerifier verifier = buildGoogleIdTokenVerifier();

        log.info("verifier: {}", verifier);

        GoogleIdToken idToken = verifier.verify(token);
        log.info("idToken: {}", idToken);

        if (idToken != null) {
            Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String picture = (String) payload.get("picture");

            User user = userService.getUserByEmail(email);

            log.info("user: {}", user);

            if (user == null) {
                UserCreateDTO userCreateDTO = buildUserCreateDTO(email, name, picture);
                String userCreatedUuid = userService.createUser(userCreateDTO);
                user = userService.getUserByUuid(userCreatedUuid);
                String tokenGenerated = tokenService.generateToken(user);
                return new LoginResponse(tokenGenerated);
            }

            String tokenGenerated = tokenService.generateToken(user);
            return new LoginResponse(tokenGenerated);

        } else {
            throw new Exception("Invalid ID token");
        }
    }

    @Override
    public RegisterResponse register(UserCreateDTO userCreateDTO) {
        String userCreatedUuid = userService.createUser(userCreateDTO);
        return new RegisterResponse(userCreatedUuid);
    }

    private GoogleIdTokenVerifier buildGoogleIdTokenVerifier() {
        return new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(List.of(GOOGLE_CLIENT_ID))
                .build();
    }

    private UserCreateDTO buildUserCreateDTO(String email, String name, String picture) {
        return UserCreateDTO.builder()
                .email(email)
                .name(name)
                .profilePicture(picture)
                .password(UUID.randomUUID().toString())
                .phone("9 9999-9999")
                .build();
    }
}
