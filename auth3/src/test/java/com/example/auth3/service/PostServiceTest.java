package com.example.auth3.service;

import com.example.auth3.dto.response.PostResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class PostServiceTest {
    private PostService postService;
    @Test
    public void 게시글_등록() {

    }
}