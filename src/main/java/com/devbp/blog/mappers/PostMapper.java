package com.devbp.blog.mappers;

import com.devbp.blog.domain.CreatePostRequest;
import com.devbp.blog.domain.UpdatePostRequest;
import com.devbp.blog.domain.dtos.CreatePostRequestDto;
import com.devbp.blog.domain.dtos.PostDto;
import com.devbp.blog.domain.dtos.UpdatePostRequestDto;
import com.devbp.blog.domain.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDto toDto(Post post);


    CreatePostRequest toCreatePostRequestDto(CreatePostRequestDto createPostRequestDto);

    UpdatePostRequest toUpdatePostRequest(UpdatePostRequestDto updatePostRequestDto);
}
