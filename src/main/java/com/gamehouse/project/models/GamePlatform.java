package com.gamehouse.project.models;

import jakarta.persistence.Entity;

import java.util.List;

@Entity
public class GamePlatform extends AbstractEntity{


    private List<String> gamePlatform;

    public GamePlatform(){}


    public GamePlatform(String gameName, List<String> gamePlatform) {
        super();
        this.setName(gameName);
        this.gamePlatform = gamePlatform;
    }

}
