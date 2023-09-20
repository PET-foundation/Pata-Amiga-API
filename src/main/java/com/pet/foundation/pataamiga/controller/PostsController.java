package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.controller.responses.PostsListResponse;
import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostUpdateDTO;
import com.pet.foundation.pataamiga.service.PostsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostsController {

    private final PostsService postsService;

    @GetMapping
    @Operation(summary = "Get all posts")
    @Tag(name = "Posts")
    public ResponseEntity<List<PostsListResponse>> getAllPosts() {
        List<PostsListResponse> response = PostsListResponse.toResponse(postsService.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get post by uuid")
    @Tag(name = "Posts")
    public ResponseEntity<Posts> getPostByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(postsService.findByUuid(uuid));
    }

    @GetMapping("/user")
    @Operation(summary = "Get posts by user uuid")
    @Tag(name = "Posts")
    public ResponseEntity<List<Posts>> getPostsByUserUuid(@RequestParam String userUuid) {
        return ResponseEntity.ok(postsService.findByUserUuid(userUuid));
    }

    @GetMapping("/name")
    @Operation(summary = "Get posts by name")
    @Tag(name = "Posts")
    public ResponseEntity<List<Posts>> getPostsByName(@RequestParam String nameContains) {
        return ResponseEntity.ok(postsService.containsName(nameContains));
    }

    @PostMapping
    @Operation(summary = "Create post")
    @Tag(name = "Posts")
    public ResponseEntity<?> createPost(@RequestBody PostCreateDTO post) {
        postsService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update post")
    @Tag(name = "Posts")
    public ResponseEntity<?> updatePost(@PathVariable String uuid, @RequestBody PostUpdateDTO post) {
        postsService.update(uuid, post);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete post")
    @Tag(name = "Posts")
    public ResponseEntity<?> deletePost(@RequestParam String uuid) {
        postsService.delete(uuid);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
