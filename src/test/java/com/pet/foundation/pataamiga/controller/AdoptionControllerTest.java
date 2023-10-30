package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.domain.adoption.Adoption;
import com.pet.foundation.pataamiga.service.AdoptionService;
import com.pet.foundation.pataamiga.utils.AdoptionCreator;
import com.pet.foundation.pataamiga.utils.PostsCreator;
import com.pet.foundation.pataamiga.utils.UserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("Adoption Controller Tests")
class AdoptionControllerTest {
    @InjectMocks
    private AdoptionController adoptionController;

    @Mock
    private AdoptionService adoptionService;

    @BeforeEach
    void setUp() {
        BDDMockito.doNothing().when(adoptionService)
                .adopt(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());

        BDDMockito.when(adoptionService.findAllByUserUuid(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AdoptionCreator.returnValidAdoption()));
    }

    @Test
    @DisplayName("Should Adopt and returns 201 when successful")
    void adopt_Returns201_WhenSuccessful() {
        String userUuid = UserCreator.returnValidUser().getUuid();
        String postUuid = PostsCreator.returnValidPosts().getUuid();

        ResponseEntity<?> adopt = adoptionController.adopt(postUuid, userUuid);

        assertEquals(adopt.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    @DisplayName("Should return list of adoptions when successful with status 200")
    void getAllAdoptionsByUserUuid_ReturnsListOfAdoptions_WhenSuccessful() {
        String userUuid = UserCreator.returnValidUser().getUuid();

        ResponseEntity<List<Adoption>> allAdoptionsByUserUuid = adoptionController.getAllAdoptionsByUserUuid(userUuid);

        assertEquals(allAdoptionsByUserUuid.getStatusCode(), HttpStatus.OK);
        assertNotNull(allAdoptionsByUserUuid.getBody());
        assertFalse(allAdoptionsByUserUuid.getBody().isEmpty());
    }

}