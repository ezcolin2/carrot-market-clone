package com.example.auth3.service;

import com.example.auth3.dto.request.PostRequest;
import com.example.auth3.entity.Post;
import com.example.auth3.entity.Image;
import com.example.auth3.etc.ImageSave;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.ImageUploadException;
import com.example.auth3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post registerPost(PostRequest post, List<MultipartFile> image) {
        if (image == null) {
            throw new ImageUploadException();
        }
        Post newPost = Post.builder()
                .postTitle(post.getPostTitle())
                .writerId(post.getWriterId())
                .content(post.getContent())
                .region(post.getRegion())
                .price(post.getPrice()).build();
//        List<Image> list = new ArrayList<>();
        for (MultipartFile multipartFile : image) {
//            list.add(ImageSave.uploadImage(multipartFile));
            newPost.getImages().add(ImageSave.uploadImage(multipartFile, newPost));

        }

        return postRepository.save(newPost);
    }

    public Post getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new DataNotFoundException("해당 게시글이");
        }
//        post.get().updateVisits();
        return post.get();
    }
    public int getPostCount() {
        return Long.valueOf(postRepository.count()).intValue();

    }


    public List<Post> findAllPostByOffset(Long offset, Long limit) {
        return postRepository.findByIdBetween(offset, limit);
    }


}
