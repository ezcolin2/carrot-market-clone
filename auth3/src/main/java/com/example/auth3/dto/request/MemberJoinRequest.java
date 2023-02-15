package com.example.auth3.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinRequest {
    @NotNull(message = "이메일을 입력하세요")
    @NotBlank(message = "이메일을 입력하세요")
    @Email(message="이메일 형식을 입력하세요")
    private String userEmail;
    @NotNull(message = "비밀번호를 입력하세요")
    @NotBlank(message = "비밀번호를 입력하세요")
    private String userPwd;
    @NotNull(message = "비밀번호 확인을 입력하세요")
    @NotBlank(message = "비밀번호 확인을 입력하세요")
    private String userPwdConfirm;
    @NotNull
    @NotBlank
    private String userNickName;
}
