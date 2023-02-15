package com.example.auth3.exception;

public class PwdConfirmNotEqualException extends RuntimeException{
    public PwdConfirmNotEqualException() {
        super("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
    }
}
