package com.devbp.blog.domain.services.impl;

import com.devbp.blog.domain.entities.Category;
import com.devbp.blog.domain.repositories.CategoryRepository;
import com.devbp.blog.domain.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }
}
