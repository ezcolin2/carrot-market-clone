package com.example.auth3.service;

import com.example.auth3.entity.Member;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.DuplicatedUserIdException;
import com.example.auth3.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MemberServiceTest {
    UserRepository userRepository;

    UserService userService;
    BCryptPasswordEncoder bcryptPasswordEncoder;

    @BeforeEach
    public void beforeEach() {
        bcryptPasswordEncoder = new BCryptPasswordEncoder();
        userService = new UserService(userRepository, bcryptPasswordEncoder);
    }


    public Member join(Member member) {
        Member newMember = userRepository.save(member);
        return newMember;
    }
    public void login(Member member) {
        userService.login(member.getUserEmail(), member.getUserPwd());
    }
    @Test
    public void 회원가입_성공() {
        Member newMember = new Member();
        newMember.setUserEmail("testUser");
        newMember.setUserPwd("testUser");

        Member member = join(newMember);
        org.assertj.core.api.Assertions.assertThat(
                newMember.getUserEmail().equals(member.getUserEmail())
        );


    }

    @Test
    public void 회원가입_아이디_중복() {
        Member newMember = new Member();
        newMember.setUserEmail("testUser");
        newMember.setUserPwd("testUser");
        join(newMember);

        Member newMember2 = new Member();
        newMember2.setUserEmail("testUser");
        newMember2.setUserPwd("testUser");
        assertThrows(DuplicatedUserIdException.class,
                ()->userService.join(newMember2.getUserEmail(), newMember2.getUserPwd())
        );

    }

    @Test
    public void 로그인_성공() {
        Member newMember = new Member();
        newMember.setUserEmail("testUser");
        newMember.setUserPwd("testUser");
        Member joinMember = join(newMember);
        login(joinMember);

    }

    @Test
    public void 로그인_실패_아이디_없음() {
        Member newMember = new Member();
        newMember.setUserEmail("testUser");
        newMember.setUserPwd("testUser");
        assertThrows(DataNotFoundException.class,
                ()->login(newMember));
    }
}
