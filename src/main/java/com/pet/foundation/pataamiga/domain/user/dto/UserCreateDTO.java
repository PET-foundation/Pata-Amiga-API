package com.pet.foundation.pataamiga.domain.user.dto;

import com.pet.foundation.pataamiga.domain.user.Contact;
import com.pet.foundation.pataamiga.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserCreateDTO(
        @NotBlank(message = "Name is mandatory")
        @Schema(description = "User name", example = "John Doe")
        String name,
        @NotBlank(message = "Email is mandatory")
        @Schema(description = "User email", example = "user@email.com")
        String email,
        @NotBlank(message = "Password is mandatory")
        @Schema(description = "User password", example = "123456")
        String password,
        @NotBlank(message = "Phone is mandatory")
        @Schema(description = "User phone", example = "123456")
        String phone,

        @Schema(description = "User whatsapp", example = "123456")
        String whatsapp,

        @Schema(description = "User instagram", example = "@user")
        String instagram,

        @Schema(description = "User facebook", example = "user213")
        String facebook,

        @Schema(description = "User profile picture", example = "https://www.google.com")
        String profilePicture,

        @Schema(description = "User banner", example = "https://www.google.com")
        String banner
) {
    public User toEntity() {
        User user = new User();

        user.setName(this.name());
        user.setEmail(this.email());
        user.setPassword(this.password());
        user.setProfilePicture(this.profilePicture());
        user.setBanner(this.banner());
        user.setContact(new Contact(
                this.phone(),
                this.whatsapp(),
                this.instagram(),
                this.facebook()
        ));

        return user;
    }
}
