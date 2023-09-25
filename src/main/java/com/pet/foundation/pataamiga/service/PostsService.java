package com.pet.foundation.pataamiga.service;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostUpdateDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsService {
    List<Posts> findAll();

    Posts findByUuid(String uuid);

    List<Posts> findByUserUuid(String userUuid);

    List<Posts> containsName(String name);

    void save(PostCreateDTO post);

    void update(String uuid, PostUpdateDTO post, String userEmail);

    void delete(String uuid, String userEmail);
}
