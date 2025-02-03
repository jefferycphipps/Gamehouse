package com.gamehouse.project.services;


import com.gamehouse.project.models.Image;
import com.gamehouse.project.models.data.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class ImageService {


    @Autowired
    private ImageRepository imageRepository;

    private final String FOLDER_PATH = "C:\\Users\\j_inf\\OneDrive\\Desktop\\images\\";
    //private final Path ROOT = Paths.get("images/");
    public int uploadImageToFileDirectory(MultipartFile file) throws IOException {
        Image tempImage = imageRepository.findByName(file.getOriginalFilename());

        String filePath = FOLDER_PATH+file.getOriginalFilename();
        System.out.println(filePath);
        Image imageData = imageRepository.save(new Image( file.getOriginalFilename(), file.getContentType(), filePath ));
        int id = imageData.getId();
        file.transferTo(new File(filePath));
        if (imageData != null) {
            return id;
        }
        return -1;
    }


    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
        Optional<Image> dbImageData = Optional.ofNullable(imageRepository.findByName(fileName));
        String filePath = dbImageData.get().getFilePath();
        byte[] images= Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    //Get file extension
    public String getFileExtension(Image image){
        if (image !=null){
            return image.getType();
        }
        return null;
    }
}