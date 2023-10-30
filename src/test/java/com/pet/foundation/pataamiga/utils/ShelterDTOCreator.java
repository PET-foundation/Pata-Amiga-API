package com.pet.foundation.pataamiga.utils;

import com.pet.foundation.pataamiga.domain.shelter.dto.ShelterCreateDTO;
import com.pet.foundation.pataamiga.domain.shelter.dto.ShelterDTO;

import java.util.List;

public class ShelterDTOCreator {
    public static ShelterCreateDTO returnValidShelterDTO() {
        return ShelterCreateDTO.builder()
                .owners(List.of(UserCreator.returnValidUser().getUuid()))
                .build();
    }
}
