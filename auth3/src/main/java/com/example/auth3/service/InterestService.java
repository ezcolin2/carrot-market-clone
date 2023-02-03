package com.example.auth3.service;

import com.example.auth3.dto.request.InterestRequest;
import com.example.auth3.dto.response.IsInterestResponse;
import com.example.auth3.entity.Interest;
import com.example.auth3.entity.Post;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.repository.InterestRepository;
import com.example.auth3.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InterestService {
    private final InterestRepository interestRepository;
    private final PostRepository postRepository;

    public Interest registerInterest(InterestRequest interest) {
        Interest newInterest = InterestRequest.toEntity(interest);
        Interest newPost = interestRepository.save(newInterest);
        Optional<Post> post = postRepository.findByPostId(interest.getPostId());

        if (post.isEmpty()) {
            throw new DataNotFoundException("해당 게시글이");
        }
        post.get().plusInterests();
        return newPost;
    }
    public IsInterestResponse isInterest(InterestRequest interest) {
        Optional<Interest> findInterest =
                interestRepository.findInterestByUserIdAndPostId(interest.getUserId(), interest.getPostId());
        return new IsInterestResponse(findInterest.isPresent());

    }

    public void deleteInterest(@RequestBody InterestRequest request) {

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
