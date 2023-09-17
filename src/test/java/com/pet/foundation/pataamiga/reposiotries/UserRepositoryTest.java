package com.pet.foundation.pataamiga.reposiotries;

import com.pet.foundation.pataamiga.domain.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DisplayName("UserRepository tests")
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
                "email",
                "password",
                null,
                null,
                null,
                null,
                null
        ));
        // given
        String uuid = "uuid";
        // when
        var user = userRepository.findByUuid(uuid);
        // then
        assertEquals(uuid, user.get().getUuid());
    }

}