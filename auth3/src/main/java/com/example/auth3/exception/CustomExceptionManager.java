package com.example.auth3.exception;

import com.example.auth3.response.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionManager {

    @ExceptionHandler(WrongUserPasswordException.class)
    public ResponseEntity<JsonResponse> wrongUserPasswordrException(WrongUserPasswordException e) {
        JsonResponse res = JsonResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage()).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<JsonResponse> userIdNotFoundException(DataNotFoundException e) {
        JsonResponse res = JsonResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .httpStatus(HttpStatus.NOT_FOUND)
                .message(e.getMessage()).build();
        return new ResponseEntity<>(res, HttpStatus.OK);
        //404에러를 200으로 반환하는 이유
        //클라우드 타입에 서버를 배포했는데 404 에러를 반환하면 원인 모를 오류가 나서
    }

    @ExceptionHandler(ImageUploadException.class)
    public ResponseEntity<JsonResponse> imageUploadException(ImageUploadException e) {
        JsonResponse res = JsonResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage()).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }

    @ExceptionHandler(NoImageUploadException.class)
    public ResponseEntity<JsonResponse> noImageUploadException(NoImageUploadException e) {
        JsonResponse res = JsonResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage()).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<JsonResponse> duplicateException(DuplicateException e) {
        JsonResponse res = JsonResponse.builder()
                .code(HttpStatus.CONFLICT.value())
                .httpStatus(HttpStatus.CONFLICT)
                .message(e.getMessage()).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JsonResponse> requestException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        JsonResponse res = JsonResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message("올바른 요청이 아닙니다")
                .data(errors).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }
    @ExceptionHandler(PwdConfirmNotEqualException.class)
    public ResponseEntity<JsonResponse> pwdConfirmException(PwdConfirmNotEqualException e) {
        JsonResponse res = JsonResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(e.getMessage()).build();
        return new ResponseEntity<>(res, res.getHttpStatus());
    }
}
