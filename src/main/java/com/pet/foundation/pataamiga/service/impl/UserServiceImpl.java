package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserUpdateDTO;
import com.pet.foundation.pataamiga.exceptions.EmailAlreadyExists;
import com.pet.foundation.pataamiga.exceptions.UserNotFoundException;
import com.pet.foundation.pataamiga.repositories.UserRepository;
import com.pet.foundation.pataamiga.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String createUser(UserCreateDTO userCreateDTO) {
        if (emailAlreadyExists(userCreateDTO.email()))
            throw new EmailAlreadyExists("Email already exists");

        User userSaved = userRepository.save(userCreateDTO.toEntity());
        return userSaved.getUuid();
    }

    @Override
    public User getUserByUuid(String uuid) {
        return userRepository.findByUuid(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updateUserByUuid(String uuid, UserUpdateDTO userUpdateDTO) {
        User userFound = this.getUserByUuid(uuid);

        User userToSave =
                userUpdateDTO.toEntity(userFound.getId(), userFound.getUuid());
        userToSave.setPassword(userFound.getPassword());
        userToSave.setCreatedAt(userFound.getCreatedAt());

        userRepository.save(userToSave);

    }

    @Override
    public void deleteUserByUuid(String uuid) {
        User userFound = this.getUserByUuid(uuid);

        userRepository.delete(userFound);
    }

    private boolean emailAlreadyExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
