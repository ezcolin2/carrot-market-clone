package com.example.auth3.service;

import com.example.auth3.entity.Member;
import com.example.auth3.exception.DuplicatedUserIdException;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.WrongUserPasswordException;
import com.example.auth3.repository.UserRepository;
import com.example.auth3.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    @Value("${jwt.secretKey}")
    private String secretKey;
    @Value("${jwt.expiredMs}")
    private Long expiredMs;

    public String join(String userEmail, String userPwd) {
        if (userRepository.findByUserEmail(userEmail).isPresent()){
            throw new DuplicatedUserIdException();
        }
        Member newMember = Member.builder()
                        .userEmail(userEmail)
                                .userPwd(encoder.encode(userPwd)).build();
        userRepository.save(newMember);
        return "회원가입 성공!";
    }

    public String login(String userEmail, String userPwd) {
        Optional<Member> user = userRepository.findByUserEmail(userEmail);
        if (user.isEmpty()) {
            throw new DataNotFoundException("해당 유저가");
        }
        if (encoder.matches(userPwd, user.get().getUserPwd())) {
            System.out.println(user.get().getUserPwd());
            return JwtUtil.createJwt(secretKey, expiredMs);
        }
        throw new WrongUserPasswordException();

    }

    public Member getUserByUserEmail(String userEmail) {
        Optional<Member> newUser = userRepository.findByUserEmail(userEmail);
        if (newUser.isEmpty()) {
            throw new DataNotFoundException("해당 유저가");
        }
        return newUser.get();
    }

}
