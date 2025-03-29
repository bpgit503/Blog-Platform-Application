package com.devbp.blog.services.impl;

import com.devbp.blog.domain.PostStatus;
import com.devbp.blog.domain.entities.Category;
import com.devbp.blog.domain.entities.Post;
import com.devbp.blog.domain.entities.Tag;
import com.devbp.blog.repositories.PostRepository;
import com.devbp.blog.services.CategoryService;
import com.devbp.blog.services.PostService;
import com.devbp.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryService categoryService;
    private final TagService tagService;

    @Transactional(readOnly = true)
    @Override
    public List<Post> getAllPosts(UUID categoryId, UUID tagId) {

        if (categoryId != null && tagId != null) {
            Category category = categoryService.getCategory(categoryId);
            Tag tag = tagService.getTag(tagId);
            return postRepository.findAllByStatusAndCategoryAndTagsContaining(PostStatus.PUBLISHED,  category, tag);
        }
        if (categoryId != null) {
            Category category = categoryService.getCategory(categoryId);
            return postRepository.findAllByStatusAndCategory(PostStatus.PUBLISHED,  category);
        }
        if (tagId != null) {
            Tag tag = tagService.getTag(tagId);
            return postRepository.findAllByStatusAndTagsContaining(PostStatus.PUBLISHED,  tag);
        }

        return postRepository.findAllByStatus(PostStatus.PUBLISHED);

    }
}
