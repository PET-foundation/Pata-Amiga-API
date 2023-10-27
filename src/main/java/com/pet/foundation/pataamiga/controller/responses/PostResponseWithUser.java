package com.pet.foundation.pataamiga.controller.responses;

import com.pet.foundation.pataamiga.domain.posts.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostResponseWithUser implements Serializable {

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

    public static PostResponseWithUser toResponse(com.pet.foundation.pataamiga.domain.posts.Posts post) {
        return new PostResponseWithUser(
                post.getId(),
                post.getUuid(),
                post.getName(),
                post.getDescription(),
                post.getPicture(),
                post.getLocation(),
                post.getInfo(),
                post.getUser().getUuid(),
                post.getUser().getProfilePicture(),
                post.getUser().getName(),
                post.getCreatedAt().toString()
        );
    }
}
