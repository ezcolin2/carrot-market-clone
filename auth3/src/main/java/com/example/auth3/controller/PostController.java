package com.example.auth3.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
   @GetMapping("")
   public ResponseEntity<String> getPosts(){
        return ResponseEntity.ok().body("good");
   }
}
