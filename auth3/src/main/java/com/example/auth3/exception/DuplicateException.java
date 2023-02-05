package com.example.auth3.exception;

public class DuplicateException extends RuntimeException{
    public DuplicateException(){
        super("중복됩니다.");
    }
}
