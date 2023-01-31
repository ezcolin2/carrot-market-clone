package com.example.auth3.service;

import com.example.auth3.entity.User;
import com.example.auth3.exception.DuplicatedUserIdException;
import com.example.auth3.exception.UserIdNotFoundException;
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

    public String join(String userId, String userPwd) {
        if (userRepository.findByUserId(userId).isPresent()){
            throw new DuplicatedUserIdException();
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
            throw new UserIdNotFoundException();
        }
        if (encoder.matches(userPwd, user.get().getUserPwd())) {
            System.out.println(user.get().getUserPwd());
            return JwtUtil.createJwt(secretKey, expiredMs);
        }
        throw new WrongUserPasswordException();

    }

}
