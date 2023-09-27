package com.pet.foundation.pataamiga.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record LoginDTO(

        @NotBlank(message = "The user email cannot be blank")
        @Schema(description = "This is the user's email", example = "user@email.com")
        String email,

        @NotBlank(message = "The user password cannot be blank")
        @Schema(description = "This is the user's password", example = "123456")
        String password
) {
}
