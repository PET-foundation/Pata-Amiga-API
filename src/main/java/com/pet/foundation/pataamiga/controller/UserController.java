package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.controller.responses.UserCreatedResponse;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.domain.user.dto.UserCreateDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserResponseDTO;
import com.pet.foundation.pataamiga.domain.user.dto.UserUpdateDTO;
import com.pet.foundation.pataamiga.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponseDTO> getUserByUuid(@PathVariable String uuid) {
        User user = userService.getUserByUuid(uuid);
        UserResponseDTO response = UserResponseDTO.toResponse(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<UserCreatedResponse> createUser(@RequestBody @Valid UserCreateDTO user) {
        String userSaved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserCreatedResponse(userSaved));
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<User> updateUser(@PathVariable String uuid, @RequestBody @Valid UserUpdateDTO user) {
        userService.updateUserByUuid(uuid, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<User> deleteUser(@PathVariable String uuid) {
        userService.deleteUserByUuid(uuid);
        return ResponseEntity.ok().build();
    }

}
