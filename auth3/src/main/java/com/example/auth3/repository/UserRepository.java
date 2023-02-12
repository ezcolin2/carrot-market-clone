package com.example.auth3.repository;

import com.example.auth3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    public Member save(Member member);
    public Optional<Member> findByUserEmail(String userEmail);
}
