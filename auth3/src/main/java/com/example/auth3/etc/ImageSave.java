package com.example.auth3.etc;


import com.example.auth3.exception.ImageUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageSave{
    private static String changedImageName(String originName) {
        String random = UUID.randomUUID().toString();
        return random+originName;
    }
    private static String createDirPath(String changedName) {

        return "c:\\images\\"+changedName;
        //이미지가 저장될 경로. 필요에 따라 경로 조정
    }

    public static Image uploadImage(MultipartFile image) {
        String originName = image.getOriginalFilename();
        String changedName = changedImageName(originName);
        String storedImagePath = createDirPath(changedName);
        System.out.println("storedImagePath = " + storedImagePath);

        try {
            image.transferTo(new File(storedImagePath));
        } catch (IOException e){
            throw new ImageUploadException();

        }
        return new Image(originName, storedImagePath);
    }

}
