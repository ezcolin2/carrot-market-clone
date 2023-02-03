package com.example.auth3.dto.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@AllArgsConstructor
public class TokenResponse {
    private String jwt;
}
