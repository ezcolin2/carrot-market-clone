package com.example.auth3.entity;

import com.example.auth3.constant.ItemSellStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
//필드 값이 많아서 빌더 패턴 사용
//조회수, 채팅, 관심의 경우 입력받는게 아니고 특정 행위에 따라 값을 변경시켜야 하므로 setter 추가
public class Post extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;
    @Column(name = "writer_id")
    private String writerId;
    @Column(name="post_title")
    private String postTitle;
    @Column(name="post_content")
    private String content;
    @Column(name="writer_region")
    private String region;
    @Column(name="price")
    private int price;//int 사용시 값 설정을 하지 않으면 0으로 됨
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Image> images = new ArrayList<>();
    private int chats;
    private int interests;
    private int visits;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ItemSellStatus sellStatus = ItemSellStatus.SELL;
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
