package com.pet.foundation.pataamiga.utils;

import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostUpdateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostsDTO;

public class CreatePostDTOCreator {

    public static PostCreateDTO returnValidPostCreateDTO() {
        return new PostCreateDTO(
                PostsCreator.returnValidPosts().getUser().getUuid()
        );
    }

    public static PostUpdateDTO returnValidPostUpdateDTO() {
        return new PostUpdateDTO();
    }
}
