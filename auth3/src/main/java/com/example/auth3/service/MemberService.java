package com.example.auth3.service;

import com.example.auth3.dto.request.MemberJoinRequest;
import com.example.auth3.entity.Member;
import com.example.auth3.entity.Post;
import com.example.auth3.exception.DuplicateException;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.PwdConfirmNotEqualException;
import com.example.auth3.exception.WrongUserPasswordException;
import com.example.auth3.repository.MemberRepository;
import com.example.auth3.util.JwtUtil;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public String join(MemberJoinRequest member) {
        if (memberRepository.findByMemberEmail(member.getUserEmail()).isPresent()){
            throw new DuplicateException("이메일 중복");
        }
        if (memberRepository.findByMemberNickname(member.getUserNickName()).isPresent()){
            throw new DuplicateException("별명 중복");
        }
        if (!member.getUserPwd().equals(member.getUserPwdConfirm())) {
            throw new PwdConfirmNotEqualException();
        }
        Member newMember = Member.builder()
                        .memberEmail(member.getUserEmail())
                        .memberNickname(member.getUserNickName())
                        .memberPwd(encoder.encode(member.getUserPwd())).build();
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

    public void deleteById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        List<Post> posts = member.get().getPost();
        for (Post post : posts) {
            post.setMember(null);
        }
        memberRepository.deleteById(id);
    }

}
