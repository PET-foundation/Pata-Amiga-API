package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.controller.responses.PostResponseWithUser;
import com.pet.foundation.pataamiga.controller.responses.PostsListResponse;
import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostUpdateDTO;
import com.pet.foundation.pataamiga.service.PostsService;
import com.pet.foundation.pataamiga.swagger.annotatios.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    @OkResponse
    @ForbiddenResponse
    public ResponseEntity<List<PostsListResponse>> getAllPosts() {
        List<PostsListResponse> response = PostsListResponse.toResponse(postsService.findAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{uuid}")
    @Operation(summary = "Get post by uuid")
    @Tag(name = "Posts")
    @OkResponse
    @ForbiddenResponse
    @NotFoundResponse
    public ResponseEntity<PostResponseWithUser> getPostByUuid(@PathVariable String uuid) {
        PostResponseWithUser response = PostResponseWithUser.toResponse(postsService.findByUuid(uuid));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user")
    @Operation(summary = "Get posts by user uuid")
    @Tag(name = "Posts")
    @OkResponse
    @ForbiddenResponse
    public ResponseEntity<List<PostsListResponse>> getPostsByUserUuid(@RequestParam String userUuid) {
        List<PostsListResponse> response = PostsListResponse.toResponse(postsService.findByUserUuid(userUuid));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    @Operation(summary = "Get posts by name")
    @Tag(name = "Posts")
    @OkResponse
    @ForbiddenResponse
    public ResponseEntity<List<PostsListResponse>> getPostsByName(@RequestParam String nameContains) {
        List<PostsListResponse> response = PostsListResponse.toResponse(postsService.containsName(nameContains));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Create post")
    @Tag(name = "Posts")
    @CreatedResponse
    @ForbiddenResponse
    @NotFoundResponse
    public ResponseEntity<?> createPost(@RequestBody @Valid PostCreateDTO post) {
        postsService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{uuid}")
    @Operation(summary = "Update post")
    @Tag(name = "Posts")
    @OkResponse
    @ForbiddenResponse
    @NotFoundResponse
    public ResponseEntity<?> updatePost(
            @PathVariable String uuid,
            @RequestBody @Valid PostUpdateDTO post,
            @AuthenticationPrincipal UserDetails userDetails
            ) {
        postsService.update(uuid, post, userDetails.getUsername());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete post")
    @Tag(name = "Posts")
    @NoContentResponse
    @ForbiddenResponse
    @NotFoundResponse
    public ResponseEntity<?> deletePost(
            @RequestParam String uuid,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        postsService.delete(uuid, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
