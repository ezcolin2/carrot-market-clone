package com.example.auth3.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

public class WrongUserPasswordException extends RuntimeException {
    public WrongUserPasswordException() {
        super("비밀번호가 일치하지 않습니다.");
    }
}
