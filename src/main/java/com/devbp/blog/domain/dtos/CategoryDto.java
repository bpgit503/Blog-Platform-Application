package com.devbp.blog.domain.dtos;

import com.devbp.blog.domain.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private UUID id;
    private  String name;
    private long postCount;
}
