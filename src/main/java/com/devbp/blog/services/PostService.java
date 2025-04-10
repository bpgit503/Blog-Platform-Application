package com.devbp.blog.services;

import com.devbp.blog.domain.CreatePostRequest;
import com.devbp.blog.domain.UpdatePostRequest;
import com.devbp.blog.domain.dtos.UpdatePostRequestDto;
import com.devbp.blog.domain.entities.Post;
import com.devbp.blog.domain.entities.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    Post getPost(UUID id);
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequestDto);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
}
