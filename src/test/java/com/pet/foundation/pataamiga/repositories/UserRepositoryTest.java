package com.pet.foundation.pataamiga.repositories;

import com.pet.foundation.pataamiga.domain.user.Contact;
import com.pet.foundation.pataamiga.domain.user.User;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("UserRepository tests")
@Log4j2
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.save(new User(
                1L,
                "uuid",
                "name",
                "email1",
                "password",
                "",
                "",
                new Contact(),
                new Date(),
                null
        ));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    @DisplayName("Test findByUUID")
    void should_ReturnUUID_When_FindByUUID() {
        // given
        String uuid = userRepository.findAll().get(0).getUuid();
        // when
        Optional<User> user = userRepository.findByUuid(uuid);
        // then
        log.info(user.get().getUuid());
        assertEquals(uuid, user.get().getUuid());
    }

    @Test
    @DisplayName("Test findByEmail")
    void should_ReturnUserByEmail_When_Success() {
        // given
        String email = "email1";
        // when
        var user = userRepository.findByEmail(email);
        // then
        assertEquals(email, user.getEmail());
    }

}