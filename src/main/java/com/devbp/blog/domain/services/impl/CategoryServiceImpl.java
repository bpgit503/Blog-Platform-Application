package com.devbp.blog.domain.services.impl;

import com.devbp.blog.domain.entities.Category;
import com.devbp.blog.domain.repositories.CategoryRepository;
import com.devbp.blog.domain.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllWithPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if (categoryRepository.existsByNameIgnoreCase(category.getName())) {
            String categoryName = category.getName();
            throw new IllegalArgumentException("Category already exists with name:  " + categoryName);
        }

        return categoryRepository.save(category);

    }
}
