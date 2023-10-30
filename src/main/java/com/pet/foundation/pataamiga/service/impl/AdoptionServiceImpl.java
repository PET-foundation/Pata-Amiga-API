package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.adoption.Adoption;
import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.repositories.AdoptionRepository;
import com.pet.foundation.pataamiga.service.AdoptionService;
import com.pet.foundation.pataamiga.service.PostsService;
import com.pet.foundation.pataamiga.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdoptionServiceImpl implements AdoptionService {

    private final AdoptionRepository adoptionRepository;
    private final PostsService postsService;
    private final UserService userService;

    @Override
    public void adopt(String postId, String userId) {
        User user = userService.getUserByUuid(userId);
        Posts post = postsService.findByUuid(postId);

        Adoption adoptionToSave = Adoption.builder()
                .adopter(user)
                .adopted(post)
                .build();

        adoptionRepository.save(adoptionToSave);
    }

    @Override
    public List<Adoption> findAllByUserUuid(String uuid) {
        return this.adoptionRepository.findAllByAdopterUuid(uuid);
    }
}
