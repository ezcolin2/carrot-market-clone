package com.example.auth3.dto.response;

import com.example.auth3.entity.Interest;
import com.example.auth3.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class MemberResponse {
    private String email;
    private String memberNickName;


    public static MemberResponse of (Member member) {
        return MemberResponse.builder()
                .email(member.getMemberEmail())
                .memberNickName(member.getMemberNickname()).build();
    }
}
