package com.example.auth3.controller;

import com.example.auth3.dto.response.MyInterestResponse;
import com.example.auth3.dto.response.PostResponseDto;
import com.example.auth3.dto.response.TokenResponse;
import com.example.auth3.dto.request.MemberJoinRequest;
import com.example.auth3.dto.response.MemberResponse;
import com.example.auth3.entity.Interest;
import com.example.auth3.entity.Member;
import com.example.auth3.entity.Post;
import com.example.auth3.response.JsonResponse;
import com.example.auth3.service.MemberService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/join")
    public ResponseEntity<JsonResponse> join(@RequestBody MemberJoinRequest user) {
        String message = memberService.join(user.getUserId(), user.getUserPwd());

        JsonResponse res = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .httpStatus(HttpStatus.OK)
                .data(null).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<JsonResponse> login(@RequestBody MemberJoinRequest user) {
        String token = memberService.login(user.getUserId(), user.getUserPwd());
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .message("로그인 성공")
                .httpStatus(HttpStatus.OK)
                .data(new TokenResponse(token)).build();
        return new ResponseEntity<JsonResponse>(response, response.getHttpStatus());

    }

    @GetMapping("/posts")
    public ResponseEntity<JsonResponse> getPostsByMemberEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Member user = memberService.getMemberByUserEmail(email);
        List<PostResponseDto> posts = new ArrayList<>();
        for (Post post : user.getPost()) {
            posts.add(PostResponseDto.of(post));
        }
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("나의 게시글 목록을 찾았습니다.")
                .data(posts).build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
    @GetMapping("/interests")
    public ResponseEntity<JsonResponse> getInterestsByMemberEmail() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Member user = memberService.getMemberByUserEmail(email);
        List<MyInterestResponse> interests = new ArrayList<>();
        for (Interest interest : user.getInterests()) {
            interests.add(MyInterestResponse.of(interest));
        }
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("나의 관심 목록을 찾았습니다.")
                .data(interests).build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
    @DeleteMapping//token을 보내면 여기에 담긴 id를 가지고 삭제
    public ResponseEntity<JsonResponse> deleteMember(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberService.getMemberByUserEmail(email);
        memberService.deleteByEmail(member.getId());
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("회원 탈퇴했습니다.").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
