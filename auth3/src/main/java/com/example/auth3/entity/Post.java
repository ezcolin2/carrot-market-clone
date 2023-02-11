package com.example.auth3.entity;

import com.example.auth3.constant.ItemSellStatus;
import com.example.auth3.dto.response.Image;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//필드 값이 많아서 빌더 패턴 사용
//조회수, 채팅, 관심의 경우 입력받는게 아니고 특정 행위에 따라 값을 변경시켜야 하므로 setter 추가
public class Post {
    private Long id;
    private String postTitle;
    private String writerId;
    private String content;
    private String region;
    private int price;//int 사용시 값 설정을 하지 않으면 0으로 됨
    private LocalDateTime time;
    private int chats;
    private int interests;
    private int visits;
    private List<Image> image;
    private ItemSellStatus sellStatus;
    public void updateVisits() {
        this.visits+=1;
    }

    public void plusInterests() {
        this.interests=this.interests+1;
    }
    public void minusInterests() {
        this.interests=this.interests-1;
    }



}
