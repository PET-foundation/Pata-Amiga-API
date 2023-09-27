package com.pet.foundation.pataamiga.utils;

import com.pet.foundation.pataamiga.domain.user.dto.LoginDTO;

public class AuthRequestCreator {

    public static LoginDTO returnValidLoginDTO() {
        return LoginDTO.builder()
                .email("email")
                .password("password")
                .build();
    }
}
