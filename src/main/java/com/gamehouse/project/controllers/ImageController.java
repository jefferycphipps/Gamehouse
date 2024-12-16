package com.gamehouse.project.controllers;

import com.gamehouse.project.models.Image;
import com.gamehouse.project.models.data.ImageRepository;
import com.gamehouse.project.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/getImages/save")
    public ResponseEntity<Image> saveImage(@RequestBody MultipartFile image) throws IOException {

        String uploadDirectory = "src/main/resources/static/images";
        String imagesString = "";

        imagesString = imageService.saveImageToStorage(uploadDirectory, image);
        Image temp = new Image(imagesString, image.getOriginalFilename());
        imageRepository.save(temp);

        return ResponseEntity.status(HttpStatus.CREATED).body(temp);

    }



}