package com.pet.foundation.pataamiga.repositories;

import com.pet.foundation.pataamiga.domain.adoption.Adoption;
import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@DisplayName("AdoptionRepository")
class AdoptionRepositoryTest {

    @Autowired
    private AdoptionRepository adoptionRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostsRepository postsRepository;


    @BeforeEach
    void setup() {
        User userOwnerPost = User.builder()
                .id(1L)
                .uuid(UUID.randomUUID().toString())
                .name("Test")
                .email("user1@email.com")
                .password("123456")
                .contact(null)
                .banner(null)
                .profilePicture(null)
                .createdAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .build();

        User userToAdopt = User.builder()
                .id(2L)
                .uuid("2a2e740d-68f2-4d24-aea0-2c66dbc97d12")
                .name("Test")
                .email("user2@email.com")
                .password("123456")
                .contact(null)
                .banner(null)
                .profilePicture(null)
                .createdAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .build();


        User userOwnerSaved = this.userRepository.save(userOwnerPost);
        User userSaved = this.userRepository.save(userToAdopt);


        Posts postToBeAdopted = Posts.builder()
                .id(1L)
                .uuid(UUID.randomUUID().toString())
                .info(null)
                .name("Test")
                .description("Test")
                .location("Test")
                .picture("Test")
                .user(userOwnerSaved)
                .createdAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .build();

        Posts PostSaved = this.postsRepository.save(postToBeAdopted);

        Adoption adoptionToBeSaved = Adoption.builder()
                .id(1L)
                .uuid(UUID.randomUUID())
                .adopter(userSaved)
                .adopted(PostSaved)
                .createdAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .build();

        this.adoptionRepository.save(adoptionToBeSaved);
    }

    @AfterEach
    void tearDown() {
        this.adoptionRepository.deleteAll();
        this.postsRepository.deleteAll();
        this.userRepository.deleteAll();
    }


    @Test
    @DisplayName("Should return a list of adoptions by adopter uuid")
    void shouldReturnAListOfAdoptionsByAdopterUuid() {

        List<Adoption> adoptions =
                this.adoptionRepository.findAllByAdopterUuid("2a2e740d-68f2-4d24-aea0-2c66dbc97d12");
        assertNotNull(adoptions);
    }

}