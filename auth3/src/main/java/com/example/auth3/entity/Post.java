package com.example.auth3.entity;

import com.example.auth3.etc.Image;
import lombok.*;

import java.util.List;

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
    private String region;
    private int price;
    private int chats;
    private int interests;
    private int visits;
    private List<Image> image;

}
