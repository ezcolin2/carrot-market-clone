package com.example.auth3.service;

import com.example.auth3.dto.User;
import com.example.auth3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public String join(String userId, String userPwd) {
        if (userRepository.findByUserId(userId).isPresent()){
            return "아이디가 존재합니다.";
        }
        User newUser = new User();
        newUser.setUserId(userId);
        newUser.setUserPwd(encoder.encode(userPwd));
        userRepository.save(newUser);
        return "회원가입 성공!";
    }

    public String login(String userId, String userPwd) {
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()) {
            return "해당 유저를 찾지못했습니다.";
        }
        if (encoder.matches(userPwd, user.get().getUserPwd())) {
            System.out.println(user.get().getUserPwd());
            return "로그인 성공!";
        }
        return "비밀번호가 일치하지 않습니다";

    }

}
