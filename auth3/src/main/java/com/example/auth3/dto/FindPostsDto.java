package com.example.auth3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FindPostsDto {
    private Long offset;
    private Long limit;
}
