package com.pet.foundation.pataamiga.reposiotries;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findByUuid(String uuid);

    Optional<Posts> findByName(String name);
}