package com.example.auth3.controller;

import com.example.auth3.entity.Post;
import com.example.auth3.response.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/token")
public class TokenController {
    @GetMapping("/valid")
    public ResponseEntity<JsonResponse> isTokenValid(){


        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("유효한 토큰입니다.").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
