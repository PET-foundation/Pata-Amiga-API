package com.pet.foundation.pataamiga.service;

import com.pet.foundation.pataamiga.domain.user.User;

public interface TokenService {
    String generateToken(User user);

    String validateToken(String token);
}
