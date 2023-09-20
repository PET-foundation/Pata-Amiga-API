package com.pet.foundation.pataamiga.domain.posts.dto;

import com.pet.foundation.pataamiga.domain.posts.Info;
import com.pet.foundation.pataamiga.domain.posts.Posts;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostCreateDTO extends PostsDTO {
    @NotBlank(message = "User uuid is mandatory")
    private String userUuid;

}
