package com.devbp.blog.domain.controllers;

import com.devbp.blog.domain.dtos.CategoryDto;
import com.devbp.blog.domain.repositories.CategoryRepository;
import com.devbp.blog.domain.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    public ResponseEntity<List<CategoryDto>> listCategories() {
        //TODO
        categoryService.listCategories();
        return null;
    }
}
