package com.pet.foundation.pataamiga.utils;

import com.pet.foundation.pataamiga.domain.adoption.Adoption;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class AdoptionCreator {

    public static Adoption returnValidAdoption() {
        return Adoption.builder()
                .id(1L)
                .uuid(UUID.randomUUID().toString())
                .adopted(PostsCreator.returnValidPosts())
                .adopter(UserCreator.returnValidUser())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
