package com.example.auth3.dto.request;

import com.example.auth3.entity.Post;
import lombok.*;

@AllArgsConstructor
@Getter
public class PostRequest {
    private String postTitle;
    private String writerId;
    private String time;
    private String content;
    private String region;
    private int price;

    public static Post toEntity(PostRequest post) {
        return Post.builder()
                .postTitle(post.getPostTitle())
                .writerId(post.getWriterId())
                .time(post.getTime())
                .content(post.getContent())
                .region(post.getRegion())
                .price(post.getPrice()).build();
    }

}
