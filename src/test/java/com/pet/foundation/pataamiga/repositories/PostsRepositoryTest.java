package com.pet.foundation.pataamiga.repositories;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("PostsRepository")
class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User userToBeSaved = new User(
                1L,
                "uuid",
                "name",
                "email",
                "password",
                "picture",
                null,
                null,
                null,
                null
        );
        User savedUser = userRepository.save(userToBeSaved);

        Posts postToBeSaved = new Posts(
                1L,
                "uuid",
                "name",
                "description",
                "picture",
                "location",
                null,
                savedUser,
                null,
                null
        );

        postsRepository.save(postToBeSaved);
    }


    @AfterEach
    void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("should find post by uuid")
    void should_ReturnPost_When_FindByUuid() {

        String uuid = postsRepository.findAll().get(0).getUuid();

        String uuidExpected = postsRepository.findByUuid(uuid).get().getUuid();

        assertEquals(uuid, uuidExpected);
    }

    @Test
    @DisplayName("should find post by name")
    void should_ReturnPost_When_FindByName() {
        String name = postsRepository.findAll().get(0).getName();

        String nameExpected = postsRepository.findByName(name).get(0).getName();

        assertEquals(name, nameExpected);
    }

    @Test
    @DisplayName("should find post by user uuid")
    void should_ReturnPost_When_FindByUserUuid() {
        String userUuid = postsRepository.findAll().get(0).getUser().getUuid();

        String userUuidExpected = postsRepository.findByUserUuid(userUuid).get(0).getUser().getUuid();

        assertEquals(userUuid, userUuidExpected);
    }
}