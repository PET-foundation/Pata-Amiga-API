package com.pet.foundation.pataamiga.utils;

import com.pet.foundation.pataamiga.controller.responses.LoginResponse;
import com.pet.foundation.pataamiga.controller.responses.RegisterResponse;

public class AuthResponseCreator {

    public static LoginResponse returnValidLoginResponse() {
        return LoginResponse.builder()
                .token("token")
                .build();
    }

    public static RegisterResponse returnValidRegisterResponse() {
        return RegisterResponse.builder()
                .uuid("uuid")
                .build();
    }
}
