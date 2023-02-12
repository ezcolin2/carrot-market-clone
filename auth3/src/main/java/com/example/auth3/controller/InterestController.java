package com.example.auth3.controller;

import com.example.auth3.dto.request.InterestRequest;
import com.example.auth3.entity.Post;
import com.example.auth3.entity.Member;
import com.example.auth3.response.JsonResponse;
import com.example.auth3.service.InterestService;
import com.example.auth3.service.PostService;
import com.example.auth3.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interests")
@RequiredArgsConstructor
public class InterestController {
    private final InterestService interestService;
    private final UserService userService;
    private final PostService postService;
    @PostMapping("")
    public ResponseEntity<JsonResponse> registerInterest(@RequestBody InterestRequest interest) {
        Member findMember = userService.getUserByUserEmail(interest.getUserEmail());
        Post findPost = postService.getPost(interest.getPostId());
        interestService.registerInterest(interest, findPost, findMember);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("관심 제품으로 등록했습니다.").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }


//    @PostMapping("/exist")
//    public ResponseEntity<JsonResponse> getIsInterest(@RequestBody InterestRequest interest) {
//        Interest findInterest = interestService.getInterest(interest);
//        JsonResponse response = JsonResponse.builder()
//                .code(HttpStatus.OK.value())
//                .httpStatus(HttpStatus.OK)
//                .message("관심 제품입니다.")
//                .data(new IsInterestResponse(true)).build();
//        return new ResponseEntity<>(response, response.getHttpStatus());
//    }
//    @PostMapping("/delete")
//    public ResponseEntity<JsonResponse> deleteInterest(@RequestBody InterestRequest request) {
//        Interest interest = interestService.getInterest(request);
//        interestService.deleteInterest(interest.getId());
//        JsonResponse response = JsonResponse.builder()
//                .code(HttpStatus.OK.value())
//                .httpStatus(HttpStatus.OK)
//                .message("관심 제품을 삭제했습니다.").build();
//        return new ResponseEntity<>(response, response.getHttpStatus());
//
//    }
}
