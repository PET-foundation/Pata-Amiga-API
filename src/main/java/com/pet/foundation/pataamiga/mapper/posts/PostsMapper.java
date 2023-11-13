package com.pet.foundation.pataamiga.mapper.posts;

import com.pet.foundation.pataamiga.domain.posts.Posts;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostCreateForShelterDTO;
import com.pet.foundation.pataamiga.domain.posts.dto.PostsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PostsMapper {
    PostsMapper INSTANCE = Mappers.getMapper(PostsMapper.class);

    PostCreateDTO toDTO(Posts posts);
    Posts toEntity(PostCreateDTO postCreateForShelterDTO);
}
