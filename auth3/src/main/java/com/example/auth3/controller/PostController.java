package com.example.auth3.controller;

import com.example.auth3.constant.ItemSellStatus;
import com.example.auth3.dto.request.PostChangeForm;
import com.example.auth3.dto.request.PostRequest;
import com.example.auth3.dto.response.PostDetailResponse;
import com.example.auth3.dto.response.PostResponseDto;
import com.example.auth3.entity.Post;
import com.example.auth3.dto.response.PostCountResponse;
import com.example.auth3.entity.Member;
import com.example.auth3.response.JsonResponse;
import com.example.auth3.service.PostService;
import com.example.auth3.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("")
    public ResponseEntity<JsonResponse> getAllPostByOffset(
            @RequestParam(name = "page") Long page,
            @RequestParam(name = "limit") Long limit
    ) {
        List<PostResponseDto> postDtoList = new ArrayList<>();
        Page<Post> posts = postService.findAllPostByOffset(page, limit);
        for (Post post : posts) {
            postDtoList.add(PostResponseDto.of(post));
        }

        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 가져오기 성공")
                .data(postDtoList).build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/search")
    public ResponseEntity<JsonResponse> searchPostByTitle(@RequestParam(name = "title") String title) {
        List<Post> byPostTitle = postService.findByPostTitle(title);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 가져오기 성공")
                .data(byPostTitle).build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
    @PostMapping("")
    public ResponseEntity<JsonResponse> registerPost(
            @Valid @RequestPart(value = "post", required = true) PostRequest post,
            @RequestPart(value = "image", required = true) List<MultipartFile> image
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
        PostDetailResponse res = PostDetailResponse.of(post);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 가져오기 성공")
                .data(res).build();
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

    @PutMapping("/{id}/status")
    public ResponseEntity<JsonResponse> changeItemSellStatus(
            @PathVariable("id") Long id,
            @RequestParam ItemSellStatus sellStatus
    ) {
        Post post = postService.getPost(id);
        postService.changeSellStatus(post, sellStatus);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("판매 상태 갱신 성공").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<JsonResponse> changePost(
            @PathVariable("id") Long id,
            @RequestBody PostChangeForm form) {
        Post post = postService.getPost(id);
        postService.changePost(post, form);

        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 수정 성공").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
