package com.gamehouse.project.models;

import jakarta.persistence.Entity;

@Entity
public class GameCategory extends AbstractEntity {

    private String gameCategory;

    private GameCategory(){}


    public GameCategory(String name, String gameCategory) {
        super();
        this.setName(name);
        this.gameCategory = gameCategory;
    }
}
