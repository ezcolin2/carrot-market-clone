package com.example.auth3.controller;

import com.example.auth3.dto.UserJoinDto;
import com.example.auth3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody UserJoinDto user) {
        String response = userService.join(user.getUserId(), user.getUserPwd());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserJoinDto user) {
        String response = userService.login(user.getUserId(), user.getUserPwd());
        return ResponseEntity.ok(response);

    }
}
