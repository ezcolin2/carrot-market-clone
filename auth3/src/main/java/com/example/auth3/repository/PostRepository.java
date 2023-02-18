package com.example.auth3.repository;

import com.example.auth3.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Optional<Post> findById(Long postId);

    public List<Post> findByIdBetween(Long offset, Long limit);
    @Query(value="select * from post p where p.post_title like %:postTitle% order by p.post_id desc", nativeQuery = true)
    public List<Post> findByTitle(@Param("postTitle") String postTitle);



//    void updateVisits(Long postId);
}
