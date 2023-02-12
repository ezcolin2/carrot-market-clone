package com.example.auth3.repository;

import com.example.auth3.entity.Interest;
import com.example.auth3.entity.Post;
import com.example.auth3.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    public Interest save(Interest interest);

    public Optional<Interest> findByPostAndMember(Post post, Member member);

//    public List<Interest> findInterestsByUserId(String userId);

//    public List<Interest> findByUserEmail(String userEmail);
//    public int getInterestCount(Long postId);
//    public Optional<Interest> findInterestByUserEmailAndPost
//    public Optional<Interest> findInterestByUserIdAndPostId(String userId, Long postId);
//    public void deleteInterest(Long interestId);
}
