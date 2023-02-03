package com.example.auth3.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Interest {
    private Long id; //pk
    private String userId; //관심을 누른 유저 아이디
    private Long postId; //관심을 누른 게시글 번호
}
