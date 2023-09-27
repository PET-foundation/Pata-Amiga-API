package com.pet.foundation.pataamiga.service;

import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserUpdateDTO;

import java.util.UUID;

public interface UserService {

    String createUser(UserCreateDTO userCreateDTO);

    User getUserByUuid(String uuid);

    User getUserByEmail(String email);

    void updateUserByUuid(String uuid, UserUpdateDTO userCreateDTO);

    void deleteUserByUuid(String uuid);
}
