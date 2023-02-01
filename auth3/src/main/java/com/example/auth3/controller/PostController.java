package com.example.auth3.controller;

import com.example.auth3.dto.PostDto;
import com.example.auth3.entity.Post;
import com.example.auth3.response.LoginAndJoinResponse;
import com.example.auth3.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
   @GetMapping("")
   public ResponseEntity<LoginAndJoinResponse> getAllPostByOffset(
           @RequestParam(name="offset")Long offset,
           @RequestParam(name="limit")Long limit
           ){
       List<Post> posts = postService.findAllPostByOffset(offset, limit);

       LoginAndJoinResponse response = LoginAndJoinResponse.builder()
               .code(HttpStatus.OK.value())
               .httpStatus(HttpStatus.OK)
               .message("게시글 가져오기 성공")
               .data(posts).build();
       return new ResponseEntity<>(response, response.getHttpStatus());
   }

    @PostMapping("")
    public ResponseEntity<LoginAndJoinResponse> registerPost(
            @RequestPart(value="post", required = true) PostDto post,
            @RequestPart(value="image", required = false) MultipartFile image
            ) {
        postService.registerPost(post, image);
        LoginAndJoinResponse response = LoginAndJoinResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 등록 성공").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
   @GetMapping("/{id}")
    public ResponseEntity<LoginAndJoinResponse> getPost(@PathVariable("id") Long id) {
       Post post = postService.getPost(id);
       LoginAndJoinResponse response = LoginAndJoinResponse.builder()
               .code(HttpStatus.OK.value())
               .httpStatus(HttpStatus.OK)
               .message("게시글 가져오기 성공")
               .data(post).build();
       return new ResponseEntity<>(response, response.getHttpStatus());
   }
}
