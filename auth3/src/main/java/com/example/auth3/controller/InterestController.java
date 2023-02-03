package com.example.auth3.controller;

import com.example.auth3.dto.request.InterestRequest;
import com.example.auth3.dto.response.IsInterestResponse;
import com.example.auth3.response.JsonResponse;
import com.example.auth3.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/exist")
    public ResponseEntity<JsonResponse> getIsInterest(@RequestBody InterestRequest interest) {
        IsInterestResponse isInterest = interestService.isInterest(interest);
        JsonResponse response = JsonResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("관심 제품으로 등록했습니다.")
                .data(isInterest).build();
        return new ResponseEntity<>(response, response.getHttpStatus());
    }
}
