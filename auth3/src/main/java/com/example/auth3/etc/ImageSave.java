package com.example.auth3.etc;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.auth3.entity.Image;
import com.example.auth3.entity.Post;
import com.example.auth3.exception.ImageUploadException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//@RequiredArgsConstructor
//public class ImageSave{
//    private static AmazonS3 amazonS3;
//    private static String changedImageName(String originName) {
//        String random = UUID.randomUUID().toString();
//        return random+originName;
//    }
//    private static String createDirPath(String changedName) {
//
//        return "c:\\images\\"+changedName;
//        //이미지가 저장될 경로. 필요에 따라 경로 조정
//    }
//
//    public static Image uploadImage(MultipartFile image, Post post) {
//        String originName = image.getOriginalFilename();
//        String changedName = changedImageName(originName);
//        amazonS3.putObject(new PutObjectRequest(
//
//        ))
//        String storedImagePath = createDirPath(changedName);
//        System.out.println("storedImagePath = " + storedImagePath);
//
//        try {
//            image.transferTo(new File(storedImagePath));
//        } catch (IOException e){
//            throw new ImageUploadException();
//
//        }
//        Image newImage = Image.builder()
//                .originName(originName)
//                .storedImagePath(storedImagePath)
//                .post(post).build();
//        return newImage;
//    }
//
//}
