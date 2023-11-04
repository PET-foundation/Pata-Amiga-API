package com.pet.foundation.pataamiga.controller.responses;

import com.pet.foundation.pataamiga.domain.posts.Info;
import com.pet.foundation.pataamiga.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostsListResponse implements Serializable {
    private Long id;
    private String uuid;
    private String name;
    private String description;
    private String picture;
    private String location;
    private Info info;
    private String userUuid;
    private String userPicture;
    private String userName;
    private String createdAt;

    public static List<PostsListResponse> toResponse(List<Posts> posts) {
        return posts.stream().map(post -> new PostsListResponse(
                post.getId(),
                post.getUuid(),
                post.getName(),
                post.getDescription(),
                post.getPicture(),
                post.getLocation(),
                post.getInfo(),
                post.getUser() != null ? post.getUser().getUuid() : "",
                post.getUser() != null ? post.getUser().getProfilePicture() : "",
                post.getUser() != null ? post.getUser().getName() : "",
                post.getCreatedAt().toString()
        )).toList();
    }
}
