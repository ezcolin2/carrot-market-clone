package com.example.auth3.repository;

import com.example.auth3.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemoryUserRepository implements UserRepository{
    private final BCryptPasswordEncoder encoder;
    private static Map<Long, User> users = new HashMap<>();
    private static Long sequence = 0L;
    public void clearUsers() {
        users = new HashMap<>();
    }

    public User save(User user) {

        users.put(++sequence, user);
        return user;
    }

    public Optional<User> findByUserId(String userId){
        return users.values().stream()
                .filter(e->e.getUserId().equals(userId)).findAny();
    }
}
