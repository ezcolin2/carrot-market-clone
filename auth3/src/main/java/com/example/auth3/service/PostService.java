package com.example.auth3.service;

import com.example.auth3.constant.ItemSellStatus;
import com.example.auth3.dto.request.PostChangeForm;
import com.example.auth3.dto.request.PostRequest;
import com.example.auth3.entity.Image;
import com.example.auth3.entity.Post;
import com.example.auth3.entity.Member;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.ImageUploadException;
import com.example.auth3.repository.PostRepository;
import com.example.auth3.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ImageS3Service imageService;


    public Post registerPost(PostRequest post, List<MultipartFile> image, Member member) {
        if (image == null) {
            throw new ImageUploadException();
        }
//        User user = userRepository.findByUserId(post.getWriterId()).get();
        Post newPost = Post.createEntity(post, member);
//        List<Image> list = new ArrayList<>();
        for (MultipartFile multipartFile : image) {
//            list.add(ImageSave.uploadImage(multipartFile));
            newPost.getImages().add(imageService.uploadImage(multipartFile, newPost));

        }

        return postRepository.save(newPost);
    }

    public Post getPost(Long postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new DataNotFoundException("해당 게시글이");
        }
        post.get().updateVisits();
        return post.get();
    }
    public int getPostCount() {
        return Long.valueOf(postRepository.count()).intValue();

    }


    public Page<Post> findAllPostByOffset(Long page, Long limit) {
        Pageable pageable = PageRequest.of(Long.valueOf(page).intValue(), Long.valueOf(limit).intValue());
        return postRepository.findAll(pageable);
    }

    public List<Post> findByPostTitle(String title) {
        List<Post> posts = postRepository.findByTitle(title);
        return posts;
    }


    public void changePost(Post post, PostChangeForm form) {
        post.changePost(form);
    }

    public void changeSellStatus(Post post, ItemSellStatus sellStatus) {
        post.changeSellStatus(sellStatus);
    }

    public void deletePostById(Long id) {//S3에 존재하는 이미지 전부 삭제하고 레포지토리에서 삭제
        Post post = getPost(id);
        List<Image> images = post.getImages();
        for (Image image : images) {
            String storedImagePath = image.getStoredImagePath();
            String key = storedImagePath.substring(storedImagePath.lastIndexOf("/")+1);
            imageService.deleteImage(key);
        }
        postRepository.deleteById(id);
    }

}
