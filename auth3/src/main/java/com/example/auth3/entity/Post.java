package com.example.auth3.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    private Long id;
    private String postTitle;
    private String writerId;
    private String time;
    private String content;

}
