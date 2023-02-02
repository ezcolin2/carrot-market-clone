package com.example.auth3.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    private Long id;
    private String postTitle;
    private String writerId;
    private String time;
    private String content;
    private String region;
    private int price;

}
