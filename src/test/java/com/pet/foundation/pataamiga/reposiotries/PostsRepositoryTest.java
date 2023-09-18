package com.pet.foundation.pataamiga.reposiotries;

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

    @BeforeEach
    void setUp() {
        Posts postToBeSaved = new Posts(
                1L,
                "uuid",
                "name",
                "description",
                "picture",
                "location",
                null,
                new User(
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
                ),
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

        String nameExpected = postsRepository.findByName(name).get().getName();

        assertEquals(name, nameExpected);
    }

}