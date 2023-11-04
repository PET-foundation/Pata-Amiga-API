package com.pet.foundation.pataamiga.repositories;

import com.pet.foundation.pataamiga.domain.adoption.Adoption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, UUID> {
    List<Adoption> findAllByAdopterUuid(String uuid);
}
