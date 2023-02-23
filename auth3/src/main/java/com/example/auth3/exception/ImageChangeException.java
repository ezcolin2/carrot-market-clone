package com.example.auth3.exception;

public class ImageChangeException extends RuntimeException {
    public ImageChangeException() {
        super("이미지는 반드시 하나 이상 포함해야 합니다.");
    }
}
