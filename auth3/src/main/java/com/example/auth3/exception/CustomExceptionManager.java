package com.example.auth3.exception;

import com.example.auth3.response.LoginAndJoinResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionManager {
    @ExceptionHandler(DuplicatedUserIdException.class)
    public ResponseEntity<LoginAndJoinResponse> duplicatedUserIdException(DuplicatedUserIdException e) {
        LoginAndJoinResponse res = LoginAndJoinResponse.builder()
                .code(HttpStatus.CONFLICT.value())
                .httpStatus(HttpStatus.CONFLICT)
                .message(e.getMessage()).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }

    @ExceptionHandler(WrongUserPasswordException.class)
    public ResponseEntity<LoginAndJoinResponse> wrongUserPasswordrException(WrongUserPasswordException e) {
        LoginAndJoinResponse res = LoginAndJoinResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage()).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<LoginAndJoinResponse> userIdNotFoundException(UserIdNotFoundException e) {
        LoginAndJoinResponse res = LoginAndJoinResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(e.getMessage()).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }
}
