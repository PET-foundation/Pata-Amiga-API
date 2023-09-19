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

    public static List<PostsListResponse> toResponse(List<Posts> posts) {
        return posts.stream().map(post -> new PostsListResponse(
                post.getId(),
                post.getUuid(),
                post.getName(),
                post.getDescription(),
                post.getPicture(),
                post.getLocation(),
                post.getInfo(),
                post.getUser().getUuid()
        )).toList();
    }
}
