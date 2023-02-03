package com.example.auth3.entity;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class User {
    private Long id;
    private String userId;
    private String userPwd;
}
