package com.example.auth3.controller;

import com.example.auth3.entity.Post;
import com.example.auth3.response.LoginAndJoinResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
   @GetMapping("")
   public ResponseEntity<LoginAndJoinResponse> getPosts(){
       Post testPost = Post.builder()
               .postTitle("테스트 제목")
               .writerId("테스트 유저")
               .time("2022-01-01")
               .id(1L).build();
       List<Post> li = new ArrayList<>();
       li.add(testPost);
       li.add(testPost);
       li.add(testPost);
       li.add(testPost);
       li.add(testPost);
       li.add(testPost);
       li.add(testPost);
       li.add(testPost);
       li.add(testPost);
       li.add(testPost);
       LoginAndJoinResponse response = LoginAndJoinResponse.builder()
               .code(HttpStatus.OK.value())
               .httpStatus(HttpStatus.OK)
               .message("모든 게시글 가져오기 성공")
               .data(li).build();
       return new ResponseEntity<>(response, response.getHttpStatus());
   }

    @PostMapping("")
    public ResponseEntity<LoginAndJoinResponse> postPost() {
        LoginAndJoinResponse response = LoginAndJoinResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 등록 성공").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
   @GetMapping("/{id}")
    public ResponseEntity<LoginAndJoinResponse> getPost(@PathVariable("id") Long id) {
       Post testPost = Post.builder()
               .postTitle("테스트 제목")
               .writerId("테스트 유저")
               .time("2022-01-01")
               .content("테스트 내용")
               .id(1L).build();
       LoginAndJoinResponse response = LoginAndJoinResponse.builder()
               .code(HttpStatus.OK.value())
               .httpStatus(HttpStatus.OK)
               .message("게시글 가져오기 성공")
               .data(testPost).build();
       return new ResponseEntity<>(response, response.getHttpStatus());
   }
}
