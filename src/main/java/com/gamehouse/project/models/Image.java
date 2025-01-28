package com.gamehouse.project.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;


@Entity
public class Image extends AbstractEntity{

    private String type;

    private String filePath;

    @OneToOne
    private User user;

    public Image() {
    }

    public Image(String name, String type,String filePath){
        super();
        this.setName(name);
        this.type = type;
        this.filePath = filePath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
