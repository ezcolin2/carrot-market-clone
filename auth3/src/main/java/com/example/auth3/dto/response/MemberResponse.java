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
    private List<InterestResponse> interests;
    public static MemberResponse of (Member member) {
        List<InterestResponse> interestResponses = new ArrayList<>();
        for (Interest interest : member.getInterests()) {
            interestResponses.add(InterestResponse.of(interest, interest.getPost()));//클라이언트에 보내줄 때는 dto 타입으로 변환하여 보내줌
        }
        return MemberResponse.builder()
                .email(member.getMemberEmail())
                .interests(interestResponses).build();
    }
}
