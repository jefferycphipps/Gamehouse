package com.gamehouse.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class GamePlatform extends AbstractEntity{


    private int igdbCode;

    @ManyToMany
    List<Game> gamesList;

    public GamePlatform(){}


    public GamePlatform(String gameName) {
        super();
        this.setName(gameName);
    }

}
