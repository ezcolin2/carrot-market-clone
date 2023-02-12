package com.example.auth3.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinRequest {
    private String userId;
    private String userPwd;
}
