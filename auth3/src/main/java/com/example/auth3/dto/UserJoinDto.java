package com.example.auth3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinDto {
    private String userId;
    private String userPwd;
}
