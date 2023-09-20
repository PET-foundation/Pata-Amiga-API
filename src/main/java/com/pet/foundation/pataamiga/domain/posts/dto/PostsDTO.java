package com.pet.foundation.pataamiga.domain.posts.dto;

import com.pet.foundation.pataamiga.domain.posts.Info;
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
    protected String name;

    @NotBlank(message = "Description is mandatory")
    protected String description;

    protected String picture;

    @NotBlank(message = "Location is mandatory")
    protected String location;

    @NotEmpty(message = "Info is mandatory")
    protected Info info;

}
