package com.example.auth3.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JsonResponse {
    private int code;
    private HttpStatus httpStatus;
    private String message;
    private Object data;
}
