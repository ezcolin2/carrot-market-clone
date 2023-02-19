package com.example.auth3.dto.response;

import com.example.auth3.constant.ItemSellStatus;
import com.example.auth3.entity.Image;
import com.example.auth3.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PostResponseDto {
    private Long id;
    private String postTitle;
    private String content;
    private String region;
    private int price;//int 사용시 값 설정을 하지 않으면 0으로 됨
    private Image thumbnail;
    private int chats;
    private int interests;
    private int visits;
    private ItemSellStatus sellStatus = ItemSellStatus.SELL;

    public static PostResponseDto of(Post post) {
        return PostResponseDto.builder()
                .id(post.getId())
                .postTitle(post.getPostTitle())
                .content(post.getContent())
                .region(post.getRegion())
                .price(post.getPrice())
                .thumbnail(post.getImages().get(0))
                .chats(post.getChats())
                .interests(post.getInterests())
                .visits(post.getVisits())
                .sellStatus(post.getSellStatus()).build();
    }
}
