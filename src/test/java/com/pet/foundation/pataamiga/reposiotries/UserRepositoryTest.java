package com.pet.foundation.pataamiga.reposiotries;

import com.pet.foundation.pataamiga.domain.user.User;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("UserRepository tests")
@Log4j2
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    @DisplayName("Test findByUUID")
    void should_ReturnUUID_When_FindByUUID() {
        userRepository.save(new User(
                1L,
                "uuid",
                "name",
                "email1",
                "password",
                null,
                null,
                null,
                null,
                null
        ));

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
        userRepository.save(new User(
                1L,
                "uuid",
                "name",
                "email",
                "password",
                null,
                null,
                null,
                null,
                null
        ));
        // given
        String email = "email";
        // when
        var user = userRepository.findByEmail(email);
        // then
        assertEquals(email, user.getEmail());
    }

}