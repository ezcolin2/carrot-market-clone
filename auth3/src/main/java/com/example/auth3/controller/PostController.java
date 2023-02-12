package com.example.auth3.controller;

import com.example.auth3.dto.request.PostRequest;
import com.example.auth3.entity.Post;
import com.example.auth3.dto.response.PostCountResponse;
import com.example.auth3.entity.Member;
import com.example.auth3.response.JsonResponse;
import com.example.auth3.service.PostService;
import com.example.auth3.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MemberService memberService;
   @GetMapping("")
   public ResponseEntity<JsonResponse> getAllPostByOffset(
           @RequestParam(name="offset")Long offset,
           @RequestParam(name="limit")Long limit
           ){
       List<Post> posts = postService.findAllPostByOffset(offset, offset+limit-1);

       JsonResponse response = JsonResponse.builder()
               .code(HttpStatus.OK.value())
               .httpStatus(HttpStatus.OK)
               .message("게시글 가져오기 성공")
               .data(posts).build();
       return new ResponseEntity<>(response, response.getHttpStatus());
   }

    @PostMapping("")
    public ResponseEntity<JsonResponse> registerPost(
            @RequestPart(value="post", required = true) PostRequest post,
            @RequestPart(value="image", required = true) List<MultipartFile> image
            ) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Member findMember = memberService.getMemberByUserEmail(email);
        postService.registerPost(post, image, findMember);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 등록 성공").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
   @GetMapping("/{id}")
    public ResponseEntity<JsonResponse> getPost(@PathVariable("id") Long id) {
       Post post = postService.getPost(id);
       JsonResponse response = JsonResponse.builder()
               .code(HttpStatus.OK.value())
               .httpStatus(HttpStatus.OK)
               .message("게시글 가져오기 성공")
               .data(post).build();
       return new ResponseEntity<>(response, response.getHttpStatus());
   }
    @GetMapping("/count")
    public ResponseEntity<JsonResponse> getPostCount() {
        PostCountResponse count = new PostCountResponse(postService.getPostCount());
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 개수 가져오기 성공")
                .data(count).build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
