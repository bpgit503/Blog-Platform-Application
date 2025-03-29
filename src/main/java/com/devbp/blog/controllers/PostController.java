package com.devbp.blog.controllers;

import com.devbp.blog.domain.dtos.PostDto;
import com.devbp.blog.mappers.PostMapper;
import com.devbp.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(@RequestParam(required = false) UUID id, @RequestParam(required = false) UUID tagId) {

        List<PostDto> postDtos = postService.getAllPosts(id, tagId).stream()
                .map(postMapper::toDto)
                .toList();

        return ResponseEntity.ok(postDtos);


    }
}
