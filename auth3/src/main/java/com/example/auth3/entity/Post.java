package com.example.auth3.entity;

import com.example.auth3.etc.Image;
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
    private Image image;

}
