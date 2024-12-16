package com.gamehouse.project.models;

import com.gamehouse.project.models.data.ImageRepository;
import jakarta.persistence.Entity;

@Entity
public class Image extends AbstractEntity{

    private String fileName;


    public Image() {
    }

    public Image(String name, String fileName){
        super();
        this.setName(name);
        this.fileName = fileName;
    }



}
