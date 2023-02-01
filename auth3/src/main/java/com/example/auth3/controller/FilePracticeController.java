package com.example.auth3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Controller
public class FilePracticeController {
    @PostMapping("/image")
    public void postImage(@RequestParam MultipartFile uploadImage) throws IOException {
        String folder = System.getProperty("user.dir")+"\\images\\";
        File folderDir = new File(folder);
        folderDir.mkdirs();

        String originalFileName = uploadImage.getOriginalFilename();
        uploadImage.transferTo(new File(folder+originalFileName));
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
