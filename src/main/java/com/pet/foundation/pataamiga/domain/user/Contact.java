package com.pet.foundation.pataamiga.domain.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Embeddable
@Builder
public class Contact {
    @Column
    @Schema(description = "Phone number of the user", example = "12345678")
    private String phone;

    @Column
    @Schema(description = "Email of the user", example = "81 123 456 789")
    private String whatsapp;

    @Column
    @Schema(description = "Instagram of the user", example = "https://www.instagram.com/pataamiga")
    private String instagram;

    @Column
    @Schema(description = "Facebook of the user", example = "https://www.facebook.com/pataamiga")
    private String facebook;
}
