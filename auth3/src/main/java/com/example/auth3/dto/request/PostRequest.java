package com.example.auth3.dto.request;

import lombok.*;

@AllArgsConstructor
@Getter
public class PostRequest {
    private String postTitle;
    private String writerEmail;
    private String time;
    private String content;
    private String region;
    private int price;



}
