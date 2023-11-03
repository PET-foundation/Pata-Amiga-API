package com.pet.foundation.pataamiga.repositories;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.utils.PostsCreator;
import com.pet.foundation.pataamiga.utils.UserCreator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("ShelterRepository tests")
class ShelterRepositoryTest {

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostsRepository postsRepository;

    @BeforeEach
    void setUp() {
        List<User> users = List.of(UserCreator.returnValidUser());
        List<User> owners = new ArrayList<>();
        List<Posts> posts = List.of(PostsCreator.returnValidPosts());
        List<Posts> postsFromShelter = new ArrayList<>();

        for (User user : users) {
            User owner = userRepository.save(user);
            owners.add(owner);
        }

        for (Posts post : posts) {
            post.setUser(owners.get(0));
            Posts postSaved = postsRepository.save(post);
            postsFromShelter.add(postSaved);
        }

        Shelter shelter = new Shelter(
                1L,
                UUID.randomUUID(),
                "name",
                "description",
                "location",
                "adoptionPolice",
                "profilePicture",
                "banner",
                null,
                null,
                owners,
                postsFromShelter,
                Date.from(Instant.now()),
                Date.from(Instant.now())
        );

        this.shelterRepository.save(shelter);
    }

    @AfterEach
    void tearDown() {
        this.shelterRepository.deleteAll();
    }

    @Test
    @DisplayName("Should return shelter by uuid")
    public void should_ReturnShelter_When_FindByUuid() {

        UUID uuid = this.shelterRepository.findAll().get(0).getUuid();

        Shelter shelter = this.shelterRepository.findShelterByUuid(uuid).get();

        assertEquals(uuid, shelter.getUuid());
    }

    @Test
    @DisplayName("Should return a list of shelter by owners uuid")
    public void should_ReturnListOfShelter_When_FindByOwnersUuid() {

        String uuid = this.shelterRepository.findAll().get(0).getOwners().get(0).getUuid();

        List<Shelter> shelters = this.shelterRepository.findSheltersByOwnersUuid(uuid);

        String actualUuid = shelters.get(0).getOwners().get(0).getUuid();

        assertEquals(uuid, actualUuid);
    }

}