package com.example.auth3.service;

import com.example.auth3.entity.Post;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.repository.PostRepository;
import com.example.auth3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post registerPost(Post post) {
        return postRepository.save(post);
    }

    public Post getPost(Long postId) {
        Optional<Post> post = postRepository.findByPostId(postId);
        if (post.isEmpty()) {
            throw new DataNotFoundException("해당 게시글이");
        }
        return post.get();
    }

    public List<Post> findAllPostByOffset(Long offset, Long limit) {
        return postRepository.findAllPostByOffset(offset, limit);
    }

}
