package com.example.auth3.controller;

import com.example.auth3.dto.response.MyInterestResponse;
import com.example.auth3.dto.response.PostResponseDto;
import com.example.auth3.dto.response.TokenResponse;
import com.example.auth3.dto.request.MemberJoinRequest;
import com.example.auth3.entity.Interest;
import com.example.auth3.entity.Member;
import com.example.auth3.entity.Post;
import com.example.auth3.response.JsonResponse;
import com.example.auth3.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<JsonResponse> join(@Valid @RequestBody MemberJoinRequest user) {
        String message = memberService.join(user);

        JsonResponse res = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .httpStatus(HttpStatus.OK)
                .data(null).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<JsonResponse> login(@RequestBody MemberJoinRequest user) {
        String token = memberService.login(user.getUserEmail(), user.getUserPwd());
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .message("로그인 성공")
                .httpStatus(HttpStatus.OK)
                .data(new TokenResponse(token)).build();
        return new ResponseEntity<JsonResponse>(response, response.getHttpStatus());

    }

    @DeleteMapping//token을 보내면 여기에 담긴 id를 가지고 삭제
    public ResponseEntity<JsonResponse> deleteMember(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberService.getMemberByUserEmail(email);
        memberService.deleteById(member.getId());
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("회원 탈퇴했습니다.").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
