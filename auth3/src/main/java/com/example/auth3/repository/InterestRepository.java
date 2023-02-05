package com.example.auth3.repository;

import com.example.auth3.dto.request.InterestRequest;
import com.example.auth3.entity.Interest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface InterestRepository {
    public Interest save(Interest interest);

    public List<Interest> findInterestsByUserId(String userId);
    public int getInterestCount(Long postId);
    public Optional<Interest> findInterestByUserIdAndPostId(String userId, Long postId);
    public void deleteInterest(Long interestId);
}
