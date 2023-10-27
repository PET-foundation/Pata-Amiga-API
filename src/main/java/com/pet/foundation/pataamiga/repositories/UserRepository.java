package com.pet.foundation.pataamiga.repositories;

import com.pet.foundation.pataamiga.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuid(String uuid);
    User findByEmail(String email);
}
