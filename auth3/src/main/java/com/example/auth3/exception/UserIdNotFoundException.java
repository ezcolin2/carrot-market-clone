package com.example.auth3.exception;

public class UserIdNotFoundException extends RuntimeException {
    public UserIdNotFoundException(){
        super("해당 아이디를 가진 유저가 없습니다.");
    }
}
