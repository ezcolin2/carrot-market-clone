package com.example.auth3.exception;

public class DuplicatedUserIdException extends RuntimeException{
    public DuplicatedUserIdException() {
        super("아이디가 이미 존재합니다.");
    }
}
