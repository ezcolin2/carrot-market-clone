package com.example.auth3.dto.response;

import com.example.auth3.entity.Interest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MyInterestResponse {
    private Long id;
    private PostResponseDto post;

    public static MyInterestResponse of(Interest interest) {
        return MyInterestResponse.builder()
                .id(interest.getId())
                .post(PostResponseDto.of(interest.getPost())).build();
    }
}
