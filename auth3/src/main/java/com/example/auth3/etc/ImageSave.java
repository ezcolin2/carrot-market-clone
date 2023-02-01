package com.example.auth3.etc;


import com.example.auth3.exception.ImageUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class ImageSave{
    @Value("${upload.image.path}")
    private static String imageFolderPath;
    private static String changedImageName(String originName) {
        String random = UUID.randomUUID().toString();
        return random+originName;
    }
    private static String createDirPath(String changedName) {

        return "c:\\images\\"+changedName;
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
