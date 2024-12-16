package com.gamehouse.project.services;


import com.gamehouse.project.models.Image;
import com.gamehouse.project.models.data.ImageRepository;
import com.gamehouse.project.utility.ImageUtility;
import com.sun.tools.jconsole.JConsoleContext;
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

    private final String FOLDER_PATH = "C:\\Users\\j_inf\\OneDrive\\Desktop\\images\\";
    //private final Path ROOT = Paths.get("images/");
    public String uploadImageToFileDirectroy(MultipartFile file) throws IOException {




        String filePath = FOLDER_PATH+file.getOriginalFilename();
        System.out.println(filePath);
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
