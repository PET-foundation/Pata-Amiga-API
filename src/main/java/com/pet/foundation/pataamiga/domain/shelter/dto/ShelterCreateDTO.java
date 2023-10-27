package com.pet.foundation.pataamiga.domain.shelter.dto;


import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.domain.user.Contact;
import com.pet.foundation.pataamiga.domain.user.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ShelterCreateDTO extends ShelterDTO {

    @Schema(description = "Shelter's owners uuids", example = "[\"uuid1\", \"uuid2\"]")
    private List<String> owners;


    public ShelterCreateDTO() {
        super();
    }

}
