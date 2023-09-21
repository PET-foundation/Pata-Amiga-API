package com.pet.foundation.pataamiga.controller;

import com.pet.foundation.pataamiga.controller.responses.PostsListResponse;
import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostUpdateDTO;
import com.pet.foundation.pataamiga.exceptions.PostNotFoundException;
import com.pet.foundation.pataamiga.exceptions.UserNotFoundException;
import com.pet.foundation.pataamiga.service.PostsService;
import com.pet.foundation.pataamiga.utils.CreatePostDTOCreator;
import com.pet.foundation.pataamiga.utils.PostsCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PostsControllerTest {

    @InjectMocks
    private PostsController postsController;

    @Mock
    private PostsService postsService;


    @BeforeEach
    void setUp() {
        BDDMockito.when(postsService.findAll())
                .thenReturn(List.of(PostsCreator.returnValidPosts()));

        BDDMockito.when(postsService.findByUuid(ArgumentMatchers.anyString()))
                .thenReturn(PostsCreator.returnValidPosts());

        BDDMockito.when(postsService.findByUserUuid(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PostsCreator.returnValidPosts()));

        BDDMockito.when(postsService.containsName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PostsCreator.returnValidPosts()));

        BDDMockito.doNothing().when(postsService)
                .save(ArgumentMatchers.any(PostCreateDTO.class));

        BDDMockito.doNothing().when(postsService)
                .update(ArgumentMatchers.anyString(), ArgumentMatchers.any(PostUpdateDTO.class));

        BDDMockito.doNothing().when(postsService)
                .delete(ArgumentMatchers.anyString());
    }

    @Test
    @DisplayName("getAllPosts returns list of posts when successful")
    void getAllPosts_ReturnsListOfPosts_WhenSuccessful() {
        String expectedName = PostsCreator.returnValidPosts().getName();

        List<PostsListResponse> postsFound = postsController.getAllPosts().getBody();

        assertNotNull(postsFound);
        assertFalse(postsFound.isEmpty());
        assertEquals(expectedName, postsFound.get(0).getName());
    }

    @Test
    @DisplayName("getAllPosts returns an empty list of posts when does not find any")
    void getAllPosts_ReturnsEmptyListOfPosts_WhenDoesNotFindAny() {
        BDDMockito.when(postsService.findAll())
                .thenReturn(List.of());

        List<PostsListResponse> postsFound = postsController.getAllPosts().getBody();

        assertNotNull(postsFound);
        assertTrue(postsFound.isEmpty());
    }

    @Test
    @DisplayName("getPostByUuid returns post when successful")
    void getPostByUuid_ReturnsPost_WhenSuccessful() {
        String expectedName = PostsCreator.returnValidPosts().getName();

        Posts postsFound = postsController.getPostByUuid(expectedName).getBody();

        assertNotNull(postsFound);
        assertEquals(expectedName, postsFound.getName());
    }

    @Test
    @DisplayName("getPostByUuid throws PostNotFoundException when post is not found")
    void getPostByUuid_ThrowsPostNotFoundException_WhenPostIsNotFound() {
        BDDMockito.when(postsService.findByUuid(ArgumentMatchers.anyString()))
                .thenThrow(new PostNotFoundException("Post not found"));

        String uuidToSearch = PostsCreator.returnValidPosts().getUuid();

        assertThrows(PostNotFoundException.class, () -> postsController.getPostByUuid(uuidToSearch));
    }

    @Test
    @DisplayName("getPostsByUserUuid returns list of posts when successful")
    void getPostsByUserUuid_ReturnsListOfPosts_WhenSuccessful() {
        String expectedUserUuid = PostsCreator.returnValidPosts().getUser().getUuid();

        List<Posts> postsFound = postsController.getPostsByUserUuid(expectedUserUuid).getBody();

        assertNotNull(postsFound);
        assertFalse(postsFound.isEmpty());
        assertEquals(expectedUserUuid, postsFound.get(0).getUser().getUuid());
    }

    @Test
    @DisplayName("getPostsByUserUuid returns an empty list of posts when does not find any")
    void getPostsByUserUuid_ReturnsEmptyListOfPosts_WhenDoesNotFindAny() {
        BDDMockito.when(postsService.findByUserUuid(ArgumentMatchers.anyString()))
                .thenReturn(List.of());

        String expectedUserUuid = PostsCreator.returnValidPosts().getUser().getUuid();

        List<Posts> postsFound = postsController.getPostsByUserUuid(expectedUserUuid).getBody();

        assertNotNull(postsFound);
        assertTrue(postsFound.isEmpty());
    }

    @Test
    @DisplayName("getPostsByUserUuid throws UserNotFoundException when user is not found")
    void getPostsByUserUuid_ThrowsUserNotFoundException_WhenUserIsNotFound() {

        BDDMockito.when(postsService.findByUserUuid(ArgumentMatchers.anyString()))
                .thenThrow(new UserNotFoundException("User not found"));

        String uuidToSearch = PostsCreator.returnValidPosts().getUser().getUuid();

        assertThrows(UserNotFoundException.class, () -> postsController.getPostsByUserUuid(uuidToSearch));
    }

    @Test
    @DisplayName("getPostsByName returns list of posts when successful")
    void getPostsByName_ReturnsListOfPosts_WhenSuccessful() {
        String expectedName = PostsCreator.returnValidPosts().getName();

        List<Posts> postsFound = postsController.getPostsByName(expectedName).getBody();

        assertNotNull(postsFound);
        assertFalse(postsFound.isEmpty());
        assertEquals(expectedName, postsFound.get(0).getName());
    }

    @Test
    @DisplayName("createPost returns post when successful")
    void createPost_ReturnsPost_WhenSuccessful() {
        PostCreateDTO postsDTO = CreatePostDTOCreator.returnValidPostCreateDTO();

        ResponseEntity<?> responseEntity = postsController.createPost(postsDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("updatePost returns post when successful")
    void updatePost_ReturnsPost_WhenSuccessful() {
        String uuidToUpdate = PostsCreator.returnValidPosts().getUuid();
        PostUpdateDTO postsDTO = CreatePostDTOCreator.returnValidPostUpdateDTO();

        ResponseEntity<?> responseEntity = postsController.updatePost(uuidToUpdate, postsDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    @DisplayName("deletePost returns post when successful")
    void deletePost_ReturnsPost_WhenSuccessful() {
        String uuidToDelete = PostsCreator.returnValidPosts().getUuid();

        ResponseEntity<?> responseEntity = postsController.deletePost(uuidToDelete);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

}