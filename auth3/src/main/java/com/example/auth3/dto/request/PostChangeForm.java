package com.example.auth3.dto.request;

import com.example.auth3.entity.Image;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Getter
public class PostChangeForm {
    private String postTitle;
    private String content;
    private String region;
    private int price;//int 사용시 값 설정을 하지 않으면 0으로 됨
    private List<MultipartFile> images;
}
