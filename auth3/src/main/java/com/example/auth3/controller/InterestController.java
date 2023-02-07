package com.example.auth3.controller;

import com.example.auth3.dto.request.InterestRequest;
import com.example.auth3.dto.response.IsInterestResponse;
import com.example.auth3.entity.Interest;
import com.example.auth3.exception.DataNotFoundException;
import com.example.auth3.response.JsonResponse;
import com.example.auth3.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interests")
@RequiredArgsConstructor
public class InterestController {
    private final InterestService interestService;
    @PostMapping("")
    public ResponseEntity<JsonResponse> registerInterest(@RequestBody InterestRequest interest) {
        interestService.registerInterest(interest);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("관심 제품으로 등록했습니다.").build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("")
    public ResponseEntity<JsonResponse> findInterestsByUserId(@RequestParam String userId) {
        List<Interest> interests = interestService.findInterestsByUserId(userId);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("관심 제품들을 찾았습니다.")
                .data(interests).build();
        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @PostMapping("/exist")
    public ResponseEntity<JsonResponse> getIsInterest(@RequestBody InterestRequest interest) {
        Interest findInterest = interestService.getInterest(interest);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("관심 제품입니다.")
                .data(new IsInterestResponse(true)).build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
    @PostMapping("/delete")
    public ResponseEntity<JsonResponse> deleteInterest(@RequestBody InterestRequest request) {
        Interest interest = interestService.getInterest(request);
        interestService.deleteInterest(interest.getId());
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("관심 제품을 삭제했습니다.").build();
        return new ResponseEntity<>(response, response.getHttpStatus());

    }
}
