package com.pet.foundation.pataamiga.domain.user.dto;

import com.pet.foundation.pataamiga.domain.user.Contact;
import com.pet.foundation.pataamiga.domain.user.User;
import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Email is mandatory")
        String email,
        @NotBlank(message = "Password is mandatory")
        String password,
        @NotBlank(message = "Phone is mandatory")
        String phone,
        String whatsapp,
        String instagram,
        String facebook,
        String profilePicture,
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
