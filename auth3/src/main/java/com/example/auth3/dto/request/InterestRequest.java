package com.example.auth3.dto.request;

import com.example.auth3.entity.Interest;
import com.example.auth3.repository.InterestRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
public class InterestRequest {
    private String userId;
    private Long postId;

    public static Interest toEntity(InterestRequest request) {
        return Interest.builder()
                .postId(request.getPostId())
                .userId(request.getUserId()).build();
    }
}
