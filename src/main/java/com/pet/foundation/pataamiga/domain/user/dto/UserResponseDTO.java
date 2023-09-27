package com.pet.foundation.pataamiga.domain.user.dto;

import com.pet.foundation.pataamiga.domain.user.Contact;
import com.pet.foundation.pataamiga.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
        private Long id ;
        private String uuid;
        private String name;
        private String email;
        private String profilePicture;
        private String banner;
        private Contact contact;
        private String createdAt;
        private String updatedAt;

    public static UserResponseDTO toResponse(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUuid(),
                user.getName(),
                user.getEmail(),
                user.getProfilePicture(),
                user.getBanner(),
                user.getContact(),
                user.getCreatedAt().toString(),
                user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null
        );
    }
}
