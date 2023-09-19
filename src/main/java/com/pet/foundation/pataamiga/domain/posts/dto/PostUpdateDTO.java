package com.pet.foundation.pataamiga.domain.posts.dto;

import com.pet.foundation.pataamiga.domain.posts.Info;
import jakarta.validation.constraints.NotBlank;

public record PostUpdateDTO(
        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotBlank
        String picture,

        @NotBlank
        String location,
        Info info
) {
}
