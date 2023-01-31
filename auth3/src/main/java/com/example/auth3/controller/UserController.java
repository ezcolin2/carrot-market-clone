package com.example.auth3.controller;

import com.example.auth3.dto.TokenResponse;
import com.example.auth3.dto.UserJoinDto;
import com.example.auth3.response.LoginAndJoinResponse;
import com.example.auth3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.el.parser.Token;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<LoginAndJoinResponse> join(@RequestBody UserJoinDto user) {
        String message = userService.join(user.getUserId(), user.getUserPwd());

        LoginAndJoinResponse res = LoginAndJoinResponse.builder()
                .code(HttpStatus.OK.value())
                .message(message)
                .httpStatus(HttpStatus.OK)
                .data(null).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginAndJoinResponse> login(@RequestBody UserJoinDto user) {
        String token = userService.login(user.getUserId(), user.getUserPwd());
        LoginAndJoinResponse response = LoginAndJoinResponse.builder()
                .code(HttpStatus.OK.value())
                .message("로그인 성공")
                .httpStatus(HttpStatus.OK)
                .data(new TokenResponse(token)).build();
        return new ResponseEntity<LoginAndJoinResponse>(response, response.getHttpStatus());

    }
}
