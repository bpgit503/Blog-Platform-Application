package com.devbp.blog.services.impl;

import com.devbp.blog.domain.entities.Tag;
import com.devbp.blog.repositories.TagRepository;
import com.devbp.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }
}
