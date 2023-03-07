package com.example.auth3.service;

import com.example.auth3.dto.request.MemberJoinRequest;
import com.example.auth3.entity.Member;
import com.example.auth3.repository.InterestRepository;
import com.example.auth3.repository.MemberRepository;
import com.example.auth3.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class InterestServiceTest {
    private InterestService interestService;
    private PostRepository postRepository;
    private InterestRepository interestRepository;
    private MemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        interestService = new InterestService(interestRepository,postRepository, memberRepository);
    }

//    @Test
//    public void 관심_등록() {
//        MemberJoinRequest memberJoinRequest = new MemberJoinRequest(
//                "hello@gmail.com",
//                "password",
//                "password",
//                "nickname"
//        );
//        memberRepository
//
//
//    }

}