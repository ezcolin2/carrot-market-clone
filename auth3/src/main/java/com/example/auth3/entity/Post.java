package com.example.auth3.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {
    private Long id;
    private String postTitle;
    private String writerId;
    private String time;
    private String content;

}
