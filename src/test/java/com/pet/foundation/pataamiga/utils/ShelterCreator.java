package com.pet.foundation.pataamiga.utils;

import com.pet.foundation.pataamiga.domain.shelter.Shelter;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ShelterCreator {
    public static Shelter returnValidShelter() {
        return Shelter.builder()
                .id(1L)
                .uuid(UUID.randomUUID())
                .name("name")
                .description("description")
                .profilePicture("picture")
                .banner("banner")
                .contact(null)
                .location("location")
                .adoptionPolice("adoption police")
                .owners(List.of(UserCreator.returnValidUser()))
                .createdAt(Date.from(Instant.now()))
                .updatedAt(null)
                .build();
    }

    public static Shelter userToBeSaved() {
        return Shelter.builder()
                .name("name")
                .description("description")
                .profilePicture("picture")
                .banner("banner")
                .contact(null)
                .location("location")
                .adoptionPolice("adoption police")
                .owners(List.of(UserCreator.returnValidUser()))
                .createdAt(null)
                .updatedAt(null)
                .build();
    }
}
