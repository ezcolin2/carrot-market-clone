package com.example.auth3.repository;

import com.example.auth3.entity.Interest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//@Repository
//public class MemoryInterestRepository implements InterestRepository{
//    private Map<Long, Interest> interests = new HashMap<>();
//    private Long sequence = 0L;
//    @Override
//    public Interest save(Interest interest) {
//        interest.setId(++sequence);
//        interests.put(interest.getId(), interest);
//        return interest;
//    }
//
//    @Override
//    public List<Interest> findInterestsByUserId(String userId) {
//        return interests.values().stream().filter(
//                e->e.getUserEmail().equals(userId)
//        ).toList();
//    }
//
//    @Override
//    public Optional<Interest> findInterestByUserIdAndPostId(String userId, Long postId) {
//        return interests.values().stream().filter(
//                e->e.getPostId().equals(postId)&&e.getUserEmail().equals(userId)
//        ).findAny();
//    }
//
//    @Override
//    public int getInterestCount(Long postId) {
//        return interests.values().stream().filter(
//                e->e.getPostId().equals(postId)
//        ).toList().size();//이게 맞나?
//    }
//    @Override
//    public void deleteInterest(Long interestId) {
//        interests.remove(interestId);
//    }
//}
