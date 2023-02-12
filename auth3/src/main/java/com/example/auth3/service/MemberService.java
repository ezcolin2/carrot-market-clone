package com.example.auth3.service;

import com.example.auth3.entity.Member;
import com.example.auth3.exception.DuplicatedUserIdException;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.WrongUserPasswordException;
import com.example.auth3.repository.MemberRepository;
import com.example.auth3.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.expiredMs}")
    private Long expiredMs;

    public String join(String memberEmail, String memberPwd) {
        if (memberRepository.findByMemberEmail(memberEmail).isPresent()){
            throw new DuplicatedUserIdException();
        }
        Member newMember = Member.builder()
                        .memberEmail(memberEmail)
                                .memberPwd(encoder.encode(memberPwd)).build();
        memberRepository.save(newMember);
        return "회원가입 성공!";
    }

    public String login(String memberEmail, String userPwd) {
        Optional<Member> user = memberRepository.findByMemberEmail(memberEmail);
        if (user.isEmpty()) {
            throw new DataNotFoundException("해당 유저가");
        }
        if (encoder.matches(userPwd, user.get().getMemberPwd())) {
            System.out.println(user.get().getMemberPwd());
            return JwtUtil.createJwt(secretKey, expiredMs, user.get().getMemberEmail());
        }
        throw new WrongUserPasswordException();

    }

    public Member getMemberByUserEmail(String userEmail) {
        Optional<Member> newUser = memberRepository.findByMemberEmail(userEmail);
        if (newUser.isEmpty()) {
            throw new DataNotFoundException("해당 유저가");
        }
        return newUser.get();
    }

    public Member getMemberByMemberId(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if (findMember.isEmpty()) {
            throw new DataNotFoundException("해당 유저가");
        }
        return findMember.get();
    }

    public void deleteByEmail(Long id) {
        memberRepository.deleteById(id);
    }

}
