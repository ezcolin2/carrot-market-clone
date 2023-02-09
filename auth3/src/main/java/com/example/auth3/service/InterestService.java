package com.example.auth3.service;

import com.example.auth3.dto.request.InterestRequest;
import com.example.auth3.dto.response.InterestResponse;
import com.example.auth3.dto.response.IsInterestResponse;
import com.example.auth3.entity.Interest;
import com.example.auth3.entity.Post;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.exception.DuplicateException;
import com.example.auth3.repository.InterestRepository;
import com.example.auth3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;
    private final PostRepository postRepository;

    public Interest registerInterest(InterestRequest interest) {
        if (interestRepository.findInterestByUserIdAndPostId(interest.getUserId(), interest.getPostId()).isPresent()){
            throw new DuplicateException();
        }
        Interest newInterest = InterestRequest.toEntity(interest);
        Interest newPost = interestRepository.save(newInterest);
        Optional<Post> post = postRepository.findByPostId(interest.getPostId());

        if (post.isEmpty()) {
            throw new DataNotFoundException("해당 게시글이");
        }
        post.get().plusInterests();
        return newPost;
    }
    public Interest getInterest(InterestRequest interest) {
        Optional<Interest> findInterest = interestRepository.findInterestByUserIdAndPostId(interest.getUserId(), interest.getPostId());
        if (findInterest.isEmpty()) {
            throw new DataNotFoundException("관심 상품이");
        }
        return findInterest.get();

    }

    public List<InterestResponse> findInterestsByUserId(String userId) {
        return interestRepository.findInterestsByUserId(userId).stream().map(
                e->InterestResponse.of(e, postRepository.findByPostId(e.getPostId()).get())
        ).toList();

    }

    public void deleteInterest(Long interestId) {

        interestRepository.deleteInterest(interestId);

    }
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
