package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.exceptions.ShelterNotFoundException;
import com.pet.foundation.pataamiga.repositories.PostsRepository;
import com.pet.foundation.pataamiga.repositories.ShelterRepository;
import com.pet.foundation.pataamiga.service.UserService;
import com.pet.foundation.pataamiga.utils.PostsCreator;
import com.pet.foundation.pataamiga.utils.ShelterCreator;
import com.pet.foundation.pataamiga.utils.UserCreator;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("ShelterService tests")
class ShelterServiceImplTest {

    @InjectMocks
    private ShelterServiceImpl shelterService;

    @Mock
    private ShelterRepository shelterRepository;

    @Mock
    private UserService userService;

    @Mock
    private PostsRepository postsRepository;

    @BeforeEach
    void setUp() {
        BDDMockito.when(shelterRepository.findAll())
                .thenReturn(List.of(ShelterCreator.returnValidShelter()));

        BDDMockito.when(shelterRepository.findSheltersByOwnersUuid(ArgumentMatchers.anyString()))
                .thenReturn(List.of(ShelterCreator.returnValidShelter()));

        BDDMockito.when(shelterRepository.findShelterByUuid(ArgumentMatchers.any()))
                .thenReturn(Optional.of(ShelterCreator.returnValidShelter()));
        BDDMockito.when(shelterRepository.save(ArgumentMatchers.any(Shelter.class)))
                .thenReturn(ShelterCreator.returnValidShelter());

        BDDMockito.doNothing().when(shelterRepository).delete(ArgumentMatchers.any(Shelter.class));

        BDDMockito.when(userService.getUserByUuid(ArgumentMatchers.anyString()))
                .thenReturn(UserCreator.returnValidUser());

        BDDMockito.when(postsRepository.save(ArgumentMatchers.any(Posts.class)))
                .thenReturn(PostsCreator.returnValidPosts());
    }

    @Test
    @DisplayName("Should return list of shelters when getAllShelters is called")
    void should_ReturnListOfShelters_When_GetAllShelters() {

        List<Shelter> shelters = shelterService.getAllShelters();
        Long expectedId = ShelterCreator.returnValidShelter().getId();
        Long actualId = shelters.get(0).getId();

        assertNotNull(shelters);
        assertEquals(1, shelters.size());
        assertEquals(expectedId, actualId);
    }

    @Test
    @DisplayName("Should return list of shelters when getAllSheltersFromUser is called")
    void should_ReturnListOfShelters_When_GetAllSheltersFromUser() {
        String userUuid = UserCreator.returnValidUser().getUuid();

        List<Shelter> shelters = shelterService.getAllSheltersFromUser(userUuid);
        Long expectedId = ShelterCreator.returnValidShelter().getId();
        Long actualId = shelters.get(0).getId();

        assertNotNull(shelters);
        assertEquals(1, shelters.size());
        assertEquals(expectedId, actualId);
    }

    @Test
    @DisplayName("Should return shelter when getShelterByUUID is called")
    void should_ReturnShelter_When_GetShelterByUUID() {
        UUID uuid = ShelterCreator.returnValidShelter().getUuid();

        Shelter shelter = shelterService.getShelterByUUID(uuid).get();
        Long expectedId = ShelterCreator.returnValidShelter().getId();
        Long actualId = shelter.getId();

        assertNotNull(shelter);
        assertEquals(expectedId, actualId);
    }

    @Test
    @DisplayName("Should throw ShelterNotFoundException when getShelterByUUID is called and shelter does not exist")
    void should_ThrowShelterNotFoundException_When_GetShelterByUUID() {
        BDDMockito.when(shelterRepository.findShelterByUuid(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        UUID uuid = ShelterCreator.returnValidShelter().getUuid();

        assertThrows(ShelterNotFoundException.class, () -> shelterService.getShelterByUUID(uuid));
    }

    @Test
    @DisplayName("Should save shelter when createShelter is called")
    void should_SaveShelter_When_CreateShelter() {
        Shelter shelter = ShelterCreator.returnValidShelter();

        shelterService.createShelter(shelter);

        BDDMockito.verify(shelterRepository, BDDMockito.times(1)).save(shelter);
    }

    @Test
    @DisplayName("Should save shelter without throwing exception when createShelter is called")
    void should_SaveShelter_WithoutThrowingException_When_CreateShelter() {
        Shelter shelter = ShelterCreator.returnValidShelter();

        assertDoesNotThrow(() -> shelterService.createShelter(shelter));
    }

    @Test
    @DisplayName("Should delete shelter without deleteShelter throws an exception")
    void should_DeleteShelter_Without_Throws_A_exception() {
        Shelter shelter = ShelterCreator.returnValidShelter();
        UUID uuid = shelter.getUuid();

        assertDoesNotThrow(() -> shelterService.deleteShelter(uuid));
    }

    @Test
    @DisplayName("Should throw ShelterNotFoundException when deleteShelter is called and shelter does not exist")
    void should_ThrowShelterNotFoundException_When_DeleteShelter() {
        BDDMockito.when(shelterRepository.findShelterByUuid(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        UUID uuid = ShelterCreator.returnValidShelter().getUuid();

        assertThrows(ShelterNotFoundException.class, () -> shelterService.deleteShelter(uuid));
    }

    @Test
    @DisplayName("Should update shelter without updateShelter throws an exception")
    void should_UpdateShelter_Without_Throws_A_Exception() {
        Shelter shelter = ShelterCreator.returnValidShelter();
        UUID uuid = shelter.getUuid();

        assertDoesNotThrow(() -> shelterService.updateShelter(uuid, shelter));
    }

    @Test
    @DisplayName("Should throw ShelterNotFoundException when updateShelter is called and shelter does not exist")
    void should_ThrowShelterNotFoundException_When_UpdateShelter() {
        BDDMockito.when(shelterRepository.findShelterByUuid(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        Shelter shelter = ShelterCreator.returnValidShelter();
        UUID uuid = shelter.getUuid();

        assertThrows(ShelterNotFoundException.class, () -> shelterService.updateShelter(uuid, shelter));
    }

    @Test
    @DisplayName("Should create a post for shelter")
    void should_Create_A_Post_For_Shelter() {
        Posts postToBeSaved = PostsCreator.returnAPostTobeSaved();
        Shelter shelterToCreatePost = ShelterCreator.returnValidShelter();
        assertDoesNotThrow(() ->
                shelterService.createPostForShelter(postToBeSaved, shelterToCreatePost.getUuid())
        );
    }

}