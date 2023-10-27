package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostUpdateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostsDTO;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.exceptions.PostNotFoundException;
import com.pet.foundation.pataamiga.repositories.PostsRepository;
import com.pet.foundation.pataamiga.service.PostsService;
import com.pet.foundation.pataamiga.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;

    private final UserService userService;

    @Override
    public List<Posts> findAll() {
        return postsRepository.findAll();
    }

    @Override
    public Posts findByUuid(String uuid) {
        return postsRepository.findByUuid(uuid)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
    }

    @Override
    public List<Posts> findByUserUuid(String userUuid) {
        return postsRepository.findByUserUuid(userUuid);
    }

    @Override
    public List<Posts> containsName(String name) {
        return postsRepository.findByName(name);
    }

    @Override
    public void save(PostCreateDTO post) {
        User userFound = userService.getUserByUuid(post.getUserUuid());

        Posts postToBeSaved = toEntity(post, userFound);

        postsRepository.save(postToBeSaved);
    }

    @Override
    public void update(String uuid, PostUpdateDTO post, String userEmail) {
        Posts postFound = this.findByUuid(uuid);

        verifyIfUserIsOwnerOfPost(userEmail, postFound);

        Posts postToBeUpdate = toEntity(post, postFound);

        postsRepository.save(postToBeUpdate);
    }

    @Override
    public void delete(String uuid, String userEmail) {
        Posts postFound = this.findByUuid(uuid);

        verifyIfUserIsOwnerOfPost(userEmail, postFound);
        postsRepository.delete(postFound);
    }

    private Posts toEntity(PostsDTO postsDTO, Posts posts) {
        posts.setName(postsDTO.getName());
        posts.setDescription(postsDTO.getDescription());
        posts.setPicture(postsDTO.getPicture());
        posts.setLocation(postsDTO.getLocation());
        if (postsDTO.getInfo() != null)
            posts.setInfo(postsDTO.getInfo());
        return posts;
    }

    private Posts toEntity(PostsDTO postsDTO, User user) {
        Posts posts = new Posts();
        posts.setUser(user);
        posts.setName(postsDTO.getName());
        posts.setDescription(postsDTO.getDescription());
        posts.setPicture(postsDTO.getPicture());
        posts.setLocation(postsDTO.getLocation());
        posts.setInfo(postsDTO.getInfo());
        return posts;
    }

    private void verifyIfUserIsOwnerOfPost(String userEmail, Posts post) {
        if (!isUserOwnerOfPost(userEmail, post)) {
            throw new IllegalArgumentException("You can not update other user's posts");
        }
    }

    private boolean isUserOwnerOfPost(String userEmail, Posts post) {
        return post.getUser().getEmail().equals(userEmail);
    }
}

