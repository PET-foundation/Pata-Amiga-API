package com.pet.foundation.pataamiga.domain.shelter.dto;

import com.pet.foundation.pataamiga.domain.user.Contact;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class ShelterDTO {

    @NotBlank(message = "Name is mandatory")
    @Schema(description = "Name of the shelter", example = "Tallinn shelter")
    protected String name;

    @NotBlank(message = "Description is mandatory")
    @Schema(description = "Description of the shelter", example = "This is a shelter in Tallinn")
    protected String description;

    @NotBlank(message = "Location is mandatory")
    @Schema(description = "Location of the shelter", example = "Tallinn")
    protected String location;

    @NotBlank(message = "Profile picture is mandatory")
    @Schema(description = "Profile picture of the shelter", example = "https://www.google.com/url?sathis_is_a_picture")
    protected String profilePicture;

    @NotBlank(message = "Banner is mandatory")
    @Schema(description = "Banner of the shelter", example = "https://www.google.com/url?sathis_is_a_picture")
    protected String banner;

    @NotNull(message = "Contact is mandatory")
    protected Contact contact = new Contact();

    @Schema(description = "Pix key from shelter", example = "shelter@email.com")
    protected String pixKey;

    @Schema(description = "Adoption Police", example = "We don't allow cows")
    protected String adoptionPolice;
}
