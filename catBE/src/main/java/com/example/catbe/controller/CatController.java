package com.example.catbe.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.example.catbe.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages = "com.example.catbe")
@RestController
@RequestMapping("/api")
public class CatController {


     @Autowired
     private AmazonS3 amazonS3;

     @Autowired
     private CatService catService;

     @GetMapping("/cats")
     public ResponseEntity<Resource> getRandomCatImage(){
               return catService.getRandomCatImage();
          }


}




