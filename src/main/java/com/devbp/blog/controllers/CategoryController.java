package com.devbp.blog.controllers;

import com.devbp.blog.domain.dtos.CategoryDto;
import com.devbp.blog.domain.dtos.CreateCategoryRequest;
import com.devbp.blog.domain.entities.Category;
import com.devbp.blog.domain.services.CategoryService;
import com.devbp.blog.mappers.CategoryMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    public static final String CATEGORY_PATH_ID = "/api/v1/categories";

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {

        List<CategoryDto> categories = categoryService.listCategories()
                .stream().map(categoryMapper::toDto)
                .toList();

        return ResponseEntity.ok(categories);
    }


    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryRequest createCategoryRequest) {

        Category category = categoryService.createCategory(categoryMapper.toEntity(createCategoryRequest));

        return new ResponseEntity<>(categoryMapper.toDto(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
