package com.devbp.blog.services;


import com.devbp.blog.domain.entities.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getTags();
}
