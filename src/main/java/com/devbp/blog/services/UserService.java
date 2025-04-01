package com.devbp.blog.services;

import com.devbp.blog.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
