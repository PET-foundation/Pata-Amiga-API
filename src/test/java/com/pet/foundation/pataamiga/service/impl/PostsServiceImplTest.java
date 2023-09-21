package com.pet.foundation.pataamiga.service.impl;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.exceptions.PostNotFoundException;
import com.pet.foundation.pataamiga.exceptions.UserNotFoundException;
import com.pet.foundation.pataamiga.reposiotries.PostsRepository;
import com.pet.foundation.pataamiga.service.UserService;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PostsServiceImplTest {

    @InjectMocks
    private PostsServiceImpl postsService;

    @Mock
    private PostsRepository postsRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        BDDMockito.when(postsRepository.findByUuid(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(PostsCreator.returnValidPosts()));

        BDDMockito.when(postsRepository.findAll())
                .thenReturn(List.of(PostsCreator.returnValidPosts()));

        BDDMockito
                .when(postsRepository.findByUserUuid(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PostsCreator.returnValidPosts()));

        BDDMockito.when(postsRepository.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(PostsCreator.returnValidPosts()));

        BDDMockito.when(postsRepository.save(ArgumentMatchers.any(Posts.class)))
                .thenReturn(PostsCreator.returnValidPosts());

        BDDMockito.when(userService.getUserByUuid(ArgumentMatchers.anyString()))
                .thenReturn(PostsCreator.returnValidPosts().getUser());
    }

    @Test
    @DisplayName("findAll returns list of posts when successful")
    void findAll_ReturnsListOfPosts_WhenSuccessful() {
        String expectedName = PostsCreator.returnValidPosts().getName();

        List<Posts> posts = postsService.findAll();

        assertNotNull(posts);
        assertFalse(posts.isEmpty());
        assertEquals(expectedName, posts.get(0).getName());
    }

    @Test
    @DisplayName("findAll returns empty list of posts when no post is found")
    void findAll_ReturnsEmptyListOfPosts_WhenNoPostIsFound() {
        BDDMockito.when(postsRepository.findAll())
                .thenReturn(List.of());

        List<Posts> posts = postsService.findAll();

        assertNotNull(posts);
        assertTrue(posts.isEmpty());
    }

    @Test
    @DisplayName("findByUuid returns post when successful")
    void findByUuid_ReturnsPost_WhenSuccessful() {
        String expectedUuid = PostsCreator.returnValidPosts().getUuid();

        Posts post = postsService.findByUuid(expectedUuid);

        assertNotNull(post);
        assertEquals(expectedUuid, post.getUuid());
    }

    @Test
    @DisplayName("findByUuid throws PostNotFoundException when post is not found")
    void findByUuid_ThrowsPostNotFoundException_WhenPostIsNotFound() {
        BDDMockito.when(postsRepository.findByUuid(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () -> postsService.findByUuid("1"));
    }

    @Test
    @DisplayName("findByName returns list of posts when successful")
    void findByName_ReturnsListOfPosts_WhenSuccessful() {
        String expectedName = PostsCreator.returnValidPosts().getName();

        List<Posts> postsFound = postsService.containsName(expectedName);

        assertNotNull(postsFound);
        assertFalse(postsFound.isEmpty());
        assertEquals(expectedName, postsFound.get(0).getName());
    }

    @Test
    @DisplayName("findByUserUuid returns list of posts when successful")
    void findByUserUuid_ReturnsListOfPosts_WhenSuccessful() {
        String expectedUuid = PostsCreator.returnValidPosts().getUser().getUuid();

        List<Posts> posts = postsService.findByUserUuid(expectedUuid);

        assertNotNull(posts);
        assertFalse(posts.isEmpty());
        assertEquals(expectedUuid, posts.get(0).getUser().getUuid());
    }

    @Test
    @DisplayName("save returns post when successful")
    void save_ReturnsPost_WhenSuccessful() {
        assertDoesNotThrow(() -> postsService.save(CreatePostDTOCreator.returnValidPostCreateDTO()));
    }

    @Test
    @DisplayName("save throws UserNotFoundException when user is not found")
    void save_ThrowsUserNotFound_WhenUserIsNotFound() {
        BDDMockito.when(userService.getUserByUuid(ArgumentMatchers.anyString()))
                .thenThrow(new UserNotFoundException("User not found"));

        assertThrows(UserNotFoundException.class, () ->
                postsService.save(CreatePostDTOCreator.returnValidPostCreateDTO()));
    }

    @Test
    @DisplayName("update updates post when successful")
    void update_UpdatesPost_WhenSuccessful() {
        assertDoesNotThrow(() -> postsService.update("1", CreatePostDTOCreator.returnValidPostUpdateDTO()));
    }

    @Test
    @DisplayName("update throws PostNotFoundException when post is not found")
    void update_ThrowsPostNotFoundException_WhenPostIsNotFound() {
        BDDMockito.when(postsRepository.findByUuid(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());

        assertThrows(PostNotFoundException.class, () ->
                postsService.update("1", CreatePostDTOCreator.returnValidPostUpdateDTO()));
    }

    @Test
    @DisplayName("delete removes post when successful")
    void delete_RemovesPost_WhenSuccessful() {
        assertDoesNotThrow(() -> postsService.delete("1"));
    }

}