package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostUpdateDTO;
import com.pet.foundation.pataamiga.domain.user.User;
import com.pet.foundation.pataamiga.reposiotries.PostsRepository;
import com.pet.foundation.pataamiga.service.PostsService;
import com.pet.foundation.pataamiga.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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
                .orElseThrow(() -> new RuntimeException("Post not found"));
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
        User userFound = userService.getUserByUuid(post.userUuid());

        Posts postToBeSaved = new Posts();
        postToBeSaved.setUser(userFound);
        postToBeSaved.setName(post.name());
        postToBeSaved.setDescription(post.description());
        postToBeSaved.setPicture(post.picture());
        postToBeSaved.setLocation(post.location());
        postToBeSaved.setInfo(post.info());
        postsRepository.save(postToBeSaved);
    }

    @Override
    public void update(String uuid, PostUpdateDTO post) {
        Posts postFound = this.findByUuid(uuid);
        postFound.setName(post.name());
        postFound.setDescription(post.description());
        postFound.setPicture(post.picture());
        postFound.setLocation(post.location());
        postFound.setInfo(post.info());

        postsRepository.save(postFound);
    }

    @Override
    public void delete(String uuid) {
        Posts postFound = this.findByUuid(uuid);
        postsRepository.delete(postFound);
    }
}

