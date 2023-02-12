package com.example.auth3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InterestRequest {
    private String userEmail;
    private Long postId;

//    public static Interest toEntity(InterestRequest request, Post post, User user) {
//        return Interest.builder()
//
//                .post(post)
//                .user(user).build();
//    }
}
