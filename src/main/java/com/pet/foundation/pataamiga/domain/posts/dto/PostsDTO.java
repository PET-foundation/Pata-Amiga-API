package com.pet.foundation.pataamiga.domain.posts.dto;

import com.pet.foundation.pataamiga.domain.posts.Info;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public abstract class PostsDTO {
    @NotBlank(message = "Name is mandatory")
    @Schema(description = "Name of the post", example = "My dog is lost")
    protected String name;

    @NotBlank(message = "Description is mandatory")
    @Schema(description = "Description of the post", example = "My dog is lost in the park")
    protected String description;

    @Schema(description = "Picture of the post", example = "https://www.google.com/url?sathis_is_a_picture")
    protected String picture;

    @NotBlank(message = "Location is mandatory")
    @Schema(description = "Location of the post", example = "Tallinn")
    protected String location;

    @NotEmpty(message = "Info is mandatory")
    protected Info info;

}
