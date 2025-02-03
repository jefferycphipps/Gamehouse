package com.gamehouse.project.controllers;

import com.gamehouse.project.models.Image;
import com.gamehouse.project.models.User;
import com.gamehouse.project.models.data.ImageRepository;
import com.gamehouse.project.models.data.UserRepository;
import com.gamehouse.project.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/saveImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        int uploadImage = imageService.uploadImageToFileDirectory(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) throws IOException {
        byte[] imageData=imageService.downloadImageFromFileSystem(fileName);
        String fileType = imageService.getFileExtension(imageRepository.findByName(fileName));
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(fileType))
                .body(imageData);

    }
    @GetMapping("image/{username}")
    public ResponseEntity<?> downloadUserImage(@PathVariable String username) throws IOException {
        User user = userRepository.findByUsername(username);
        if(user!=null){

            Image userImage = user.getProfileImage();
            byte[] imageData=imageService.downloadImageFromFileSystem(userImage.getName());
            String fileType = imageService.getFileExtension(userImage);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf(fileType))
                    .body(imageData);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no image");

    }

}