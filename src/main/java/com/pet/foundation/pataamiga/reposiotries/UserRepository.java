package com.pet.foundation.pataamiga.reposiotries;

import com.pet.foundation.pataamiga.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuid(String uuid);
    User findByEmail(String email);
}
