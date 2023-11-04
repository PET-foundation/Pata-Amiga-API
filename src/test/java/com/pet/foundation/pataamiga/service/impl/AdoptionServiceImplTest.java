package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.adoption.Adoption;
import com.pet.foundation.pataamiga.repositories.AdoptionRepository;
import com.pet.foundation.pataamiga.service.PostsService;
import com.pet.foundation.pataamiga.service.UserService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DisplayName("AdoptionServiceImplTest")
class AdoptionServiceImplTest {

    @InjectMocks
    private AdoptionServiceImpl adoptionService;

    @Mock
    private AdoptionRepository adoptionRepository;

    @Mock
    private PostsService postsService;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(adoptionRepository.save(ArgumentMatchers.any(Adoption.class)))
                .thenReturn(AdoptionCreator.returnValidAdoption());

        BDDMockito.when(adoptionRepository.findAllByAdopterUuid(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AdoptionCreator.returnValidAdoption()));

        BDDMockito.when(userService.getUserByUuid(ArgumentMatchers.anyString()))
                .thenReturn(UserCreator.returnValidUser());

        BDDMockito.when(postsService.findByUuid(ArgumentMatchers.anyString()))
                .thenReturn(PostsCreator.returnValidPosts());
    }

    @Test
    @DisplayName("Should adopt a post without throw any exception")
    void adopt_WhenSuccessful() {
        String userUuid = UserCreator.returnValidUser().getUuid();
        String postUuid = PostsCreator.returnValidPosts().getUuid();

        assertDoesNotThrow(() -> adoptionService.adopt(postUuid, userUuid));
    }

    @Test
    @DisplayName("Should return a list of adoptions by user uuid")
    void findAllByUserUuid_WhenSuccessful() {
        String userUuid = UserCreator.returnValidUser().getUuid();
        List<Adoption> adoptions = adoptionService.findAllByUserUuid(userUuid);
        assertNotNull(adoptions);
        assertFalse(adoptions.isEmpty());

        assertEquals(adoptions.get(0).getAdopter().getUuid(), userUuid);
    }

}