package com.pet.foundation.pataamiga.domain.shelter.dto;


import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.domain.user.Contact;
import com.pet.foundation.pataamiga.domain.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ShelterCreateDTO extends ShelterDTO {

    private List<Long> owners;


    public ShelterCreateDTO() {
        super();
    }

}
