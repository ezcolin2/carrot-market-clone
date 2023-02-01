package com.example.auth3.repository;

import com.example.auth3.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Repository
public class MemoryPostRepository implements PostRepository{
    private static Map<Long, Post> posts = new HashMap<>();
    private static Long sequence = 0L;

    public void clearPosts() {
        posts = new HashMap<>();
    }
    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        posts.put(post.getId(), post);
        return post;
    }

    @Override
    public Optional<Post> findByPostId(Long postId) {
        return posts.values().stream()
                .filter(e -> e.getId() == postId).findAny();
    }

    @Override
    public List<Post> findAllPostByOffset(Long offset, Long limit) {
        return posts.values().stream()
                .filter(e -> e.getId() >= offset && e.getId() < offset + limit).toList();
    }
}
