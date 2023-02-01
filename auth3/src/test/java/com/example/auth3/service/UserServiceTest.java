package com.example.auth3.service;

import com.example.auth3.entity.User;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.DuplicatedUserIdException;
import com.example.auth3.repository.MemoryUserRepository;
import com.example.auth3.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserServiceTest {
    UserRepository userRepository;

    UserService userService;
    BCryptPasswordEncoder bcryptPasswordEncoder;

    @BeforeEach
    public void beforeEach() {
        bcryptPasswordEncoder = new BCryptPasswordEncoder();
        userRepository = new MemoryUserRepository(bcryptPasswordEncoder);
        userService = new UserService(userRepository, bcryptPasswordEncoder);
    }


    public User join(User user) {
        User newUser = userRepository.save(user);
        return newUser;
    }
    public void login(User user) {
        userService.login(user.getUserId(), user.getUserPwd());
    }
    @Test
    public void 회원가입_성공() {
        User newUser = new User();
        newUser.setUserId("testUser");
        newUser.setUserPwd("testUser");

        User user = join(newUser);
        org.assertj.core.api.Assertions.assertThat(
                newUser.getUserId().equals(user.getUserId())
        );


    }

    @Test
    public void 회원가입_아이디_중복() {
        User newUser = new User();
        newUser.setUserId("testUser");
        newUser.setUserPwd("testUser");
        join(newUser);

        User newUser2 = new User();
        newUser2.setUserId("testUser");
        newUser2.setUserPwd("testUser");
        assertThrows(DuplicatedUserIdException.class,
                ()->userService.join(newUser2.getUserId(), newUser2.getUserPwd())
        );

    }

    @Test
    public void 로그인_성공() {
        User newUser = new User();
        newUser.setUserId("testUser");
        newUser.setUserPwd("testUser");
        User joinUser = join(newUser);
        login(joinUser);

    }

    @Test
    public void 로그인_실패_아이디_없음() {
        User newUser = new User();
        newUser.setUserId("testUser");
        newUser.setUserPwd("testUser");
        assertThrows(DataNotFoundException.class,
                ()->login(newUser));
    }
}
