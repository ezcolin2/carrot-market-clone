package com.example.auth3.service;

import com.example.auth3.dto.request.InterestRequest;
import com.example.auth3.entity.Interest;
import com.example.auth3.entity.Post;
import com.example.auth3.entity.Member;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.DuplicateException;
import com.example.auth3.repository.InterestRepository;
import com.example.auth3.repository.PostRepository;
import com.example.auth3.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void registerInterest(Post post, Member member) {
        if (interestRepository.findByPostAndMember(post, member).isPresent()){
            throw new DuplicateException();
        }
        Interest newInterest = Interest.builder()
                .post(post)
                .member(member).build();

        interestRepository.save(newInterest);

//        Interest newPost = interestRepository.save(newInterest);

//        Optional<Post> post = postRepository.findById(interest.getPostId());
//
//        if (post.isEmpty()) {
//            throw new DataNotFoundException("해당 게시글이");
//        }
        post.plusInterests();
    }

    public void deleteInterest(Post post, Member member) {
        Optional<Interest> interest = interestRepository.findByPostAndMember(post, member);
        if (interest.isEmpty()){
            throw new DataNotFoundException("관심 상품이");
        }
        interestRepository.delete(interest.get());
    }

    public Interest getInterest(Post post, Member member) {
        Optional<Interest> interest = interestRepository.findByPostAndMember(post, member);
        if (interest.isEmpty()){
            throw new DataNotFoundException("관심 상품이");
        }
        return interest.get();
    }

//    public Interest getInterest(InterestRequest interest) {
//        Optional<Interest> findInterest = interestRepository.findInterestByUserIdAndPostId(interest.getUserEmail(), interest.getPostId());
//        if (findInterest.isEmpty()) {
//            throw new DataNotFoundException("관심 상품이");
//        }
//        return findInterest.get();
//
//    }

//    public List<InterestResponse> findInterestsByUserId(String userId) {
//        return interestRepository.findByUserEmail(userId).stream().map(
//                e->InterestResponse.of(e, postRepository.findById(e.getPostId()).get())
//        ).toList();
//
//    }

//    public void deleteInterest(Long interestId) {
//
//        interestRepository.deleteInterest(interestId);
//
//    }
//    public void deleteInterest(Interest interest) {
//        Interest newPost = interestRepository.save(interest);
//        Optional<Post> post = postRepository.findByPostId(interest.getPostId());
//
//        if (post.isEmpty()) {
//            throw new DataNotFoundException("해당 게시글이");
//        }
//        post.get().minusInterests();
//    }
}
