package com.pet.foundation.pataamiga.repositories;

import com.pet.foundation.pataamiga.domain.email.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
