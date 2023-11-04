package com.pet.foundation.pataamiga.service;

import com.pet.foundation.pataamiga.domain.adoption.Adoption;

import java.util.List;

public interface AdoptionService {
    void adopt(String postId, String userId);

    List<Adoption> findAllByUserUuid(String uuid);
}
