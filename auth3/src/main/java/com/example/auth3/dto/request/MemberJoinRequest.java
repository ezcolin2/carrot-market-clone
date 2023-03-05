package com.example.auth3.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinRequest {
    @NotBlank(message = "이메일을 입력하세요")
    @Email(message="이메일 형식을 입력하세요")
    private String userEmail;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String userPwd;
    @NotBlank(message = "비밀번호 확인을 입력하세요")
    private String userPwdConfirm;
    @NotBlank
    private String userNickName;
}
