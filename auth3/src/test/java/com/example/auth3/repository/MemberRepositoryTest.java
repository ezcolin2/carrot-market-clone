package com.example.auth3.repository;

import com.example.auth3.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;


    @Test
    void save() {
        Member member = Member.builder().memberEmail("test@gmail.com")
                .memberPwd("password")
                .memberNickname("nickname").build();
        Member save = memberRepository.save(member);
        Assertions.assertThat(save.getMemberEmail()).isEqualTo(member.getMemberEmail());

    }

    @Test
    void findByMemberEmail() {
        Member member = Member.builder().memberEmail("test@gmail.com")
                .memberPwd("password")
                .memberNickname("nickname").build();
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findByMemberEmail(member.getMemberEmail());
        Assertions.assertThat(findMember.get().getMemberEmail()).isEqualTo(member.getMemberEmail());
    }

    @Test
    void findByMemberNickname() {
        Member member = Member.builder().memberEmail("test@gmail.com")
                .memberPwd("password")
                .memberNickname("nickname").build();
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findByMemberNickname(member.getMemberNickname());
        Assertions.assertThat(findMember.get().getMemberNickname()).isEqualTo(member.getMemberNickname());

    }
}