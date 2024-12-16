package com.gamehouse.project.services;


import com.gamehouse.project.models.Image;
import com.gamehouse.project.models.data.ImageRepository;
import com.gamehouse.project.utility.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageService {


    @Autowired
    private ImageRepository imageRepository;

//    public String uploadImage(MultipartFile file) throws IOException {
//
//        Image imageData = imageRepository.save(new Image( file.getOriginalFilename(), file.getContentType(), ImageUtility.decompressImage(file.getBytes())));
//
//        if (imageData != null) {
//            return "file uploaded successfully : " + file.getOriginalFilename();
//        }
//        return null;
//    }

//    public byte[] downloadImage(String fileName){
//        Optional<Image> dbImageData = imageRepository.findByName(fileName);
//        byte[] images=ImageUtility.decompressImage(dbImageData.get().getImageData());
//        return images;
//    }


    private final String FOLDER_PATH = "C:\\Users\\j_inf\\LaunchCode\\java\\cubsLC\\Jay-CubsLC\\src\\main\\resources\\static\\images";
    private final Path ROOT = Paths.get("images/");
    public String uploadImageToFileDirectoy(MultipartFile file) throws IOException {




        String filePath = ROOT+file.getOriginalFilename();
        Image imageData = imageRepository.save(new Image( file.getOriginalFilename(), file.getContentType(), filePath ));

        file.transferTo(new File(filePath));
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }


    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Image> dbImageData = imageRepository.findByName(fileName);
        String filePath = dbImageData.get().getFilePath();
        byte[] images= Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

}
