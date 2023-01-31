package com.example.auth3.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message){
        super(message+" 없습니다.");
    }
}
