package com.example.catbe.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CatService {

    private static final int MAX_IMAGE_NUMBER = 1; // Assuming you have cat1, cat2, ..., cat100

    @Autowired
    private AmazonS3 amazonS3;

    public String generateRandomImageName() {
        Random random = new Random();
        int randomImageNumber = random.nextInt(MAX_IMAGE_NUMBER) + 1; // Generate a random number between 1 and MAX_IMAGE_NUMBER
        return "cat" + randomImageNumber;
    }

    public ResponseEntity<Resource> getRandomCatImage() {
        String randomImageName = generateRandomImageName();
        String objectKey = "catimages/" + randomImageName + ".jpg";
        S3Object s3Object = amazonS3.getObject("catimagesbucket", objectKey);
        S3ObjectInputStream stream = s3Object.getObjectContent();

        Resource resource = new InputStreamResource(stream);

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
