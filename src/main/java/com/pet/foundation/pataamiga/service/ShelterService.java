package com.pet.foundation.pataamiga.service;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.shelter.Shelter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShelterService {
    List<Shelter> getAllShelters();

    List<Shelter> getAllSheltersFromUser(String uuid);

    Optional<Shelter> getShelterByUUID(UUID uuid);

    List<Posts> getAllPostsFromShelter(UUID shelterUuid);

    void createShelter(Shelter shelter);

    void createPostForShelter(PostCreateDTO posts, UUID shelterUuid);

    void updateShelter(UUID uuid, Shelter shelter);

    void deleteShelter(UUID uuid);
}
