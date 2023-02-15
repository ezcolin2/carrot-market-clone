package com.example.auth3.service;

import com.example.auth3.dto.request.MemberJoinRequest;
import com.example.auth3.entity.Member;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.DuplicateException;
import com.example.auth3.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MemberServiceTest {
    MemberRepository memberRepository;

    MemberService memberService;
    BCryptPasswordEncoder bcryptPasswordEncoder;

    @BeforeEach
    public void beforeEach() {
        bcryptPasswordEncoder = new BCryptPasswordEncoder();
        memberService = new MemberService(memberRepository, bcryptPasswordEncoder);
    }


    public Member join(Member member) {
        Member newMember = memberRepository.save(member);
        return newMember;
    }
    public void login(Member member) {
        memberService.login(member.getMemberEmail(), member.getMemberPwd());
    }
    @Test
    public void 회원가입_성공() {
        Member newMember = new Member();
        newMember.setMemberEmail("testMember");
        newMember.setMemberPwd("testMember");

        Member member = join(newMember);
        org.assertj.core.api.Assertions.assertThat(
                newMember.getMemberEmail().equals(member.getMemberEmail())
        );


    }

    @Test
    public void 회원가입_아이디_중복() {
        Member newMember = new Member();
        newMember.setMemberEmail("testMember");
        newMember.setMemberPwd("testMember");
        join(newMember);

        MemberJoinRequest newMember2 = new MemberJoinRequest("testMember", "testPwd", "testPwd","nickname");
        assertThrows(DuplicateException.class,
                ()-> memberService.join(newMember2)
        );

    }

    @Test
    public void 로그인_성공() {
        Member newMember = new Member();
        newMember.setMemberEmail("testMember");
        newMember.setMemberPwd("testMember");
        Member joinMember = join(newMember);
        login(joinMember);

    }

    @Test
    public void 로그인_실패_아이디_없음() {
        Member newMember = new Member();
        newMember.setMemberEmail("testMember");
        newMember.setMemberPwd("testMember");
        assertThrows(DataNotFoundException.class,
                ()->login(newMember));
    }
}
