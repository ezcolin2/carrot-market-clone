package com.example.auth3.repository;

import com.example.auth3.entity.User;

import java.util.Optional;

public interface UserRepository {
    public User save(User user);
    public Optional<User> findByUserId(String userId);
}
