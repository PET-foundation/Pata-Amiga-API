package com.pet.foundation.pataamiga.reposiotries;

import com.pet.foundation.pataamiga.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(String uuid);
}
