package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostsDTO;
import com.pet.foundation.pataamiga.domain.shelter.Shelter;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.exceptions.ShelterNotFoundException;
import com.pet.foundation.pataamiga.mapper.posts.PostsMapper;
import com.pet.foundation.pataamiga.repositories.PostsRepository;
import com.pet.foundation.pataamiga.repositories.ShelterRepository;
import com.pet.foundation.pataamiga.service.PostsService;
import com.pet.foundation.pataamiga.service.ShelterService;
import com.pet.foundation.pataamiga.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShelterServiceImpl implements ShelterService {

    @Autowired
    private ShelterRepository shelterRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostsService postsService;

    @Override
    public List<Shelter> getAllShelters() {
        return shelterRepository.findAll();
    }

    @Override
    public List<Shelter> getAllSheltersFromUser(String uuid) {
        return shelterRepository.findSheltersByOwnersUuid(uuid);
    }

    @Override
    public Optional<Shelter> getShelterByUUID(UUID uuid) {
        return shelterRepository.findShelterByUuid(uuid)
                .map(Optional::of)
                .orElseThrow(() -> new ShelterNotFoundException("Shelter not found"));
    }

    @Override
    public List<Posts> getAllPostsFromShelter(UUID shelterUuid) {
        Shelter shelterFound = this.getShelterByUUID(shelterUuid).get();

        return shelterFound.getPosts();
    }

    @Override
    public void createShelter(Shelter shelter) {
        List<User> owners = new ArrayList<>();
        shelter.getOwners()
                .stream()
                .map(user -> userService.getUserByUuid(user.getUuid()))
                .forEach(owners::add);
        shelter.setOwners(owners);
        shelterRepository.save(shelter);
    }

    @Override
    public void createPostForShelter(PostCreateDTO posts, UUID shelterUuid) {
        Posts postsSaved = this.postsService.save(posts);
        List<Posts> postsList = new ArrayList<>();
        postsList.add(postsSaved);

        Shelter shelterFound = this.getShelterByUUID(shelterUuid).get();

        shelterFound.setPosts(postsList);
        this.shelterRepository.save(shelterFound);

    }

    @Override
    public void updateShelter(UUID uuid, Shelter shelter) {
        Shelter shelterToUpdate = this.getShelterByUUID(uuid).get();
        BeanUtils.copyProperties(shelter, shelterToUpdate, "uuid", "id", "owners");
        shelterRepository.save(shelterToUpdate);
    }

    @Override
    public void deleteShelter(UUID uuid) {
        Shelter shelterToDelete = this.getShelterByUUID(uuid).get();
        shelterRepository.delete(shelterToDelete);
    }
}
