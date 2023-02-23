package com.example.auth3.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;
@Getter
public class PostModifyRequest {
    @NotBlank(message = "제목을 입력하세요")
    @NotNull(message = "제목을 입력하세요")
    private String postTitle;
    @NotBlank(message = "내용을 입력하세요")
    @NotNull(message = "내용을 입력하세요")
    private String content;
    @NotBlank(message = "지역을 입력하세요")
    @NotNull(message = "지역을 입력하세요")
    private String region;
    private List<String> imageUrlListForDelete;

    private int price;
}
