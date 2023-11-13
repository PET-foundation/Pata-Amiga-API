package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.controller.responses.ShelterResponse;
import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateForShelterDTO;
import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.domain.shelter.dto.ShelterCreateDTO;
import com.pet.foundation.pataamiga.service.ShelterService;
import com.pet.foundation.pataamiga.utils.ShelterCreator;
import com.pet.foundation.pataamiga.utils.ShelterDTOCreator;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("ShelterController tests")
class ShelterControllerTest {

    @InjectMocks
    private ShelterController shelterController;

    @Mock
    private ShelterService shelterService;

    @BeforeEach
    void setUp() {
        Shelter validShelter = ShelterCreator.returnValidShelter();

        BDDMockito.when(shelterService.getAllShelters())
                .thenReturn(List.of(validShelter));

        BDDMockito.when(shelterService.getAllSheltersFromUser(ArgumentMatchers.anyString()))
                .thenReturn(List.of(validShelter));

        BDDMockito.when(shelterService.getShelterByUUID(ArgumentMatchers.any()))
                .thenReturn(Optional.of(validShelter));

        BDDMockito.doNothing().when(shelterService)
                .createPostForShelter(ArgumentMatchers.any(PostCreateDTO.class), ArgumentMatchers.any());

        BDDMockito.doNothing().when(shelterService)
                .createShelter(ArgumentMatchers.any(Shelter.class));

        BDDMockito.doNothing().when(shelterService)
                .deleteShelter(ArgumentMatchers.any(UUID.class));

        BDDMockito.doNothing().when(shelterService)
                .updateShelter(ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(Shelter.class));
    }

    @Test
    @DisplayName("Should return list of shelters when getAllShelters is called")
    void should_ReturnListOfShelters_When_GetAllShelters() {
        String expectedName = ShelterCreator.returnValidShelter().getName();
        List<ShelterResponse> shelters = shelterController.getAllShelters().getBody();

        assertNotNull(shelters);
        assertFalse(shelters.isEmpty());
        assertEquals(expectedName, shelters.get(0).getName());
    }

    @Test
    @DisplayName("Should return list of shelters when getAllSheltersFromUser is called")
    void should_ReturnListOfShelters_When_GetAllSheltersFromUser() {
        String expectedName = ShelterCreator.returnValidShelter().getName();
        String userUuid = ShelterCreator.returnValidShelter().getOwners().get(0).getUuid();

        List<Shelter> shelters = shelterController
                .getAllSheltersFromUser(userUuid).getBody();

        assertNotNull(shelters);
        assertFalse(shelters.isEmpty());
        assertEquals(expectedName, shelters.get(0).getName());
    }

    @Test
    @DisplayName("Should return shelter when getShelterByUUID is called")
    void should_ReturnShelter_When_GetShelterByUUID() {
        String expectedName = ShelterCreator.returnValidShelter().getName();
        UUID uuid = ShelterCreator.returnValidShelter().getUuid();

        ShelterResponse shelter = shelterController
                .getShelterByUUID(uuid).getBody();

        assertNotNull(shelter);
        assertEquals(expectedName, shelter.getName());
    }

    @Test
    @DisplayName("Should create shelter when createShelter is called and return 201 status code")
    void should_CreateShelter_When_CreateShelter_And_Return_Status_201() {
        ShelterCreateDTO shelterDTO = ShelterDTOCreator.returnValidShelterDTO();

        ResponseEntity<?> shelterResponseEntity = shelterController.createShelter(shelterDTO);

        assertNotNull(shelterResponseEntity);
        assertEquals(HttpStatus.CREATED, shelterResponseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should create a post for a shelter without throw a exception")
    void should_Create_A_Post_For_A_Shelter() {
        UUID shelteruuid = ShelterCreator.returnValidShelter().getUuid();
        PostCreateDTO postCreateForShelterDTO = PostCreateDTO.builder()
                .build();

        assertDoesNotThrow(() ->
                this.shelterController.createPostForShelter(postCreateForShelterDTO, shelteruuid)
        );

    }

    @Test
    @DisplayName("Should update shelter when updateShelter is called and return 204 status code")
    void should_UpdateShelter_When_UpdateShelter_And_Return_Status_204() {
        ShelterCreateDTO shelterDTO = ShelterDTOCreator.returnValidShelterDTO();
        UUID uuid = ShelterCreator.returnValidShelter().getUuid();

        ResponseEntity<?> shelterResponseEntity = shelterController.updateShelter(uuid, shelterDTO);

        assertNotNull(shelterResponseEntity);
        assertEquals(HttpStatus.NO_CONTENT, shelterResponseEntity.getStatusCode());
    }

    @Test
    @DisplayName("Should delete shelter when deleteShelter is called and return 204 status code")
    void should_DeleteShelter_When_DeleteShelter_And_Return_Status_204() {
        UUID uuid = ShelterCreator.returnValidShelter().getUuid();

        ResponseEntity<?> shelterResponseEntity = shelterController.deleteShelter(uuid);

        assertNotNull(shelterResponseEntity);
        assertEquals(HttpStatus.NO_CONTENT, shelterResponseEntity.getStatusCode());
    }

}