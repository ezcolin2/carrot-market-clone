package com.example.auth3.service;

import com.example.auth3.constant.ItemSellStatus;
import com.example.auth3.dto.request.PostModifyRequest;
import com.example.auth3.dto.request.PostRequest;
import com.example.auth3.entity.Image;
import com.example.auth3.entity.Post;
import com.example.auth3.entity.Member;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.ImageChangeException;
import com.example.auth3.exception.ImageUploadException;
import com.example.auth3.repository.PostRepository;
import com.example.auth3.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        Pageable pageable = PageRequest.of(Long.valueOf(page).intValue(), Long.valueOf(limit).intValue(), Sort.by("id").descending());
        return postRepository.findAll(pageable);
    }

    public List<Post> findMyPost(Long id, Long page, Long limit) {

        return postRepository.findByUserId(id, page, limit);
    }
    public List<Post> findMyInterest(Long id, Long page, Long limit) {

        return postRepository.findInterestsByWriterId(id, page, limit);
    }

    public List<Post> findByPostTitle(String title,Long page, Long limit) {
        List<Post> posts = postRepository.findByTitle(title, page*limit, limit);
        return posts;
    }


    public void changePost(Post post, PostModifyRequest form, List<MultipartFile> images) {
        List<String> urls = form.getImageUrlListForDelete();
        if (post.getImages().size() - urls.size() + images.size() < 1) {
            throw new ImageChangeException();
        }

        //삭제된 이미지를 모두 삭제.
        for (String url : urls) {
            String key = url.substring(url.lastIndexOf("/") + 1);
            imageService.deleteImage(key);

        }
        post.getImages().removeIf(
                e -> urls.contains(e.getStoredImagePath())
        );

        //새로운 이미지 업로드
        for (MultipartFile image : images) {
            post.getImages().add(imageService.uploadImage(image, post));

            imageService.uploadImage(image, post);
        }
        //이미지를 제외한 다른 내용은 엄데이트
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
