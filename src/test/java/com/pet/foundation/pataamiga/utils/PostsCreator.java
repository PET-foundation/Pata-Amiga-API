package com.pet.foundation.pataamiga.utils;

import com.pet.foundation.pataamiga.domain.posts.Info;
import com.pet.foundation.pataamiga.domain.posts.Port;
import com.pet.foundation.pataamiga.domain.posts.Posts;

import java.util.Date;

public class PostsCreator {

    public static Posts returnValidPosts() {
        return Posts.builder()
                .id(1L)
                .uuid("1")
                .name("Teste")
                .description("description")
                .picture("picture")
                .location("location")
                .info(returnValidPostInfo())
                .user(UserCreator.returnValidUser())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
    }

    private static Info returnValidPostInfo() {
        return Info.builder()
                .age("age")
                .sex("male")
                .race("dog")
                .specie("specie")
                .port(Port.BIG)
                .castrated(true)
                .pedigree(true)
                .specialNeeds(true)
                .ungerminated(true)
                .vaccinated(true)
                .weight("weight")
                .build();
    }
}
