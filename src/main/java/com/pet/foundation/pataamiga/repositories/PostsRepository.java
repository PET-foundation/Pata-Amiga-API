package com.pet.foundation.pataamiga.repositories;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    Optional<Posts> findByUuid(String uuid);

    List<Posts> findByName(String name);
    List<Posts> findByUserUuid(String userUuid);
}
