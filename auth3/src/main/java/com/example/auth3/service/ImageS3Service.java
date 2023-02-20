package com.example.auth3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.example.auth3.entity.Image;
import com.example.auth3.entity.Post;
import com.example.auth3.exception.ImageUploadException;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.tools.shell.IO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ImageS3Service{
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;
    private String changedImageName(String originName) {
        String random = UUID.randomUUID().toString();
        return random+originName;
    }


    public Image uploadImage(MultipartFile image, Post post){
        String originName = image.getOriginalFilename();
        String changedName = changedImageName(originName);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("image/"+originName.split("\\.")[1]);
        System.out.println(metadata.getContentType());
        try {
            PutObjectResult putObjectResult = amazonS3.putObject(new PutObjectRequest(
                    bucketName, changedName, image.getInputStream(), metadata
            ).withCannedAcl(CannedAccessControlList.PublicRead));
            System.out.println("putObjectResult = " + putObjectResult.getContentMd5());

        } catch (IOException e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        String storedImagePath = amazonS3.getUrl(bucketName, changedName).toString();
        System.out.println("storedImagePath = " + storedImagePath);

//        try {
//            image.transferTo(new File(storedImagePath));
//        } catch (IOException e){
//            throw new ImageUploadException();
//
//        }
        Image newImage = Image.builder()
                .originName(originName)
                .storedImagePath(storedImagePath)
                .post(post).build();
        return newImage;
    }

}
