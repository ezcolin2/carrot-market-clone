package com.example.auth3.repository;

import com.example.auth3.entity.Post;
import com.example.auth3.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    public Post save(Post post);
    public Optional<Post> findByPostId(Long postId);

    public List<Post> findAllPostByOffset(Long offset, Long limit);
    public void clearPosts();
}
