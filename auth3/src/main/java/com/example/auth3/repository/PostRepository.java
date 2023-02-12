package com.example.auth3.repository;

import com.example.auth3.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Optional<Post> findById(Long postId);

    public List<Post> findByIdBetween(Long offset, Long limit);



//    void updateVisits(Long postId);
}
