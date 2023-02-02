package com.example.auth3.exception;

public class NoImageUploadException extends RuntimeException {
    public NoImageUploadException() {
        super("선택된 이미지가 없습니다.");
    }
}
