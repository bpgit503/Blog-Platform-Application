package com.devbp.blog.controllers;

import com.devbp.blog.domain.CreatePostRequest;
import com.devbp.blog.domain.UpdatePostRequest;
import com.devbp.blog.domain.dtos.CreatePostRequestDto;
import com.devbp.blog.domain.dtos.PostDto;
import com.devbp.blog.domain.dtos.UpdatePostRequestDto;
import com.devbp.blog.domain.entities.Post;
import com.devbp.blog.domain.entities.User;
import com.devbp.blog.mappers.PostMapper;
import com.devbp.blog.services.PostService;
import com.devbp.blog.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(required = false) UUID id, @RequestParam(required = false) UUID tagId) {

        List<PostDto> postDtos = postService.getAllPosts(id, tagId).stream()
                .map(postMapper::toDto)
                .toList();

        return ResponseEntity.ok(postDtos);
    }

    @GetMapping(path = "/drafts")
    public ResponseEntity<List<PostDto>> getDrafts(@RequestAttribute UUID userId) {
        User loggedInUser = userService.getUserById(userId);
        List<Post> draftPosts = postService.getDraftPosts(loggedInUser);
        List<PostDto> postDtos = draftPosts.stream().map(postMapper::toDto).toList();
        return ResponseEntity.ok(postDtos);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody CreatePostRequestDto createPostRequestDto, @RequestAttribute UUID userId) {
        User loggedInUser = userService.getUserById(userId);

        CreatePostRequest createPostRequest = postMapper.toCreatePostRequestDto(createPostRequestDto);

        PostDto createdPostDto = postMapper.toDto(postService.createPost(loggedInUser, createPostRequest));

        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable UUID id, @Valid @RequestBody UpdatePostRequestDto updatePostRequestDto){
        UpdatePostRequest updatePostRequest = postMapper.toUpdatePostRequest(updatePostRequestDto);
        PostDto updatePostDto = postMapper.toDto(postService.updatePost(id, updatePostRequest));
        return ResponseEntity.ok(updatePostDto);
    }
}
