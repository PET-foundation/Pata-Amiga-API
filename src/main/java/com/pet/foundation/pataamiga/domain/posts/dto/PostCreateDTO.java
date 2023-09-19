package com.pet.foundation.pataamiga.domain.posts.dto;

import com.pet.foundation.pataamiga.domain.posts.Info;
import com.pet.foundation.pataamiga.domain.posts.Posts;

public record PostCreateDTO(
        String name,
        String description,
        String picture,
        String location,
        Info info,
        String userUuid

) {
}
