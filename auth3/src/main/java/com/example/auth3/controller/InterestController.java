package com.example.auth3.controller;

import com.example.auth3.dto.request.InterestRequest;
import com.example.auth3.dto.response.IsInterestResponse;
import com.example.auth3.entity.Interest;
import com.example.auth3.entity.Post;
import com.example.auth3.entity.Member;
import com.example.auth3.response.JsonResponse;
import com.example.auth3.service.InterestService;
import com.example.auth3.service.PostService;
import com.example.auth3.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interests")
@RequiredArgsConstructor
public class InterestController {
    private final InterestService interestService;
    private final MemberService memberService;
    private final PostService postService;

    @PostMapping("/{postId}")//해당 게시글 번호를 보내면 토큰의 이메일과 게시글 번호를 통해 관심 상품 등록
    public ResponseEntity<JsonResponse> registerInterest(@PathVariable("postId") Long id) {
        Post post = postService.getPost(id);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        if (email.equals(post.getMember().getMemberEmail())) {
            JsonResponse response = JsonResponse.builder()
                    .code(HttpStatus.FORBIDDEN.value())
                    .httpStatus(HttpStatus.FORBIDDEN)
                    .message("내 게시물은 관심 목록으로 등록할 수 없습니다.").build();
            return new ResponseEntity<>(response, response.getHttpStatus());

        }

        Member findMember = memberService.getMemberByUserEmail(email);
        Post findPost = postService.getPost(id);
        interestService.registerInterest(findPost, findMember);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("관심 제품으로 등록했습니다.").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<JsonResponse> deleteInterest(@PathVariable("postId") Long id) {
        Post post = postService.getPost(id);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Member findMember = memberService.getMemberByUserEmail(email);
        Post findPost = postService.getPost(id);
        interestService.deleteInterest(findPost, findMember);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("관심 상품을 삭제했습니다.").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<JsonResponse> isInterest(@PathVariable("postId") Long id) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Member findMember = memberService.getMemberByUserEmail(email);
        Post findPost = postService.getPost(id);
        interestService.getInterest(findPost, findMember);//만약 관심 상품으로 등로되어있지 않다면 getInterest 메소드 수행 과정에서 에러를 던짐
        IsInterestResponse isInterestResponse = new IsInterestResponse(true);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("관심 상품으로 등록되어 있습니다.")
                .data(isInterestResponse).build();
        return new ResponseEntity<>(response, response.getHttpStatus());


    }


}
