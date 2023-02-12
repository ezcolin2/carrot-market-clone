package com.example.auth3.dto.response;

import com.example.auth3.entity.Interest;
import com.example.auth3.entity.Post;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterestResponse {
    private String userId; //관심을 누른 유저 아이디
    private Long postId; //관심을 누른 게시글 번호
    private Post post; //관심 목록을 조회할 때 post의 정보가 필요함

    public static InterestResponse of(Interest interest, Post post) {
        return InterestResponse.builder()
                .userId(interest.getMember().getUserEmail())
                .postId(interest.getPost().getId())
                .post(post).build();
    }
}
