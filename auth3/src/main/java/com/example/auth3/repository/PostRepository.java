package com.example.auth3.repository;

import com.example.auth3.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Optional<Post> findById(Long postId);

    @Query(value="select * from post p where p.post_title like %:postTitle% order by p.post_id desc limit :offset, :limit", nativeQuery = true)
    public List<Post> findByTitle(@Param("postTitle") String postTitle, @Param("offset") Long offset, @Param("limit") Long limit);

    @Query(value="select * from post p where p.user_id = :userId order by p.post_id desc limit :offset, :limit", nativeQuery = true)
    public List<Post> findByUserId(@Param("userId") Long userId, @Param("offset") Long offset, @Param("limit") Long limit);
    @Query(value="select * from post p where p.post_id in (select post_id from interest i where i.member_id = :id) order by p.post_id desc limit :offset, :limit", nativeQuery = true)
    public List<Post> findByWriterEmail(@Param("id") Long id, @Param("offset") Long offset, @Param("limit") Long limit);



//    void updateVisits(Long postId);
}
