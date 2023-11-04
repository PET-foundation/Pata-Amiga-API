package com.pet.foundation.pataamiga.repositories;

import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Optional<Shelter> findShelterByUuid(UUID uuid);
    List<Shelter> findSheltersByOwnersUuid(String uuid);

}
