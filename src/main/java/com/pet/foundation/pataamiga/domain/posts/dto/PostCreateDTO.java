package com.pet.foundation.pataamiga.domain.posts.dto;

import com.pet.foundation.pataamiga.domain.posts.Info;
import com.pet.foundation.pataamiga.domain.posts.Posts;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record PostCreateDTO(
        @NotBlank(message = "Name is mandatory")
        String name,
        @NotBlank(message = "Description is mandatory")
        String description,

        String picture,
        @NotBlank(message = "Location is mandatory")
        String location,
        @NotEmpty(message = "Info is mandatory")
        Info info,

        @NotBlank(message = "User uuid is mandatory")
        String userUuid

) {
}
