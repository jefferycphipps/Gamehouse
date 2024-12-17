package com.gamehouse.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class GameCategory extends AbstractEntity {

    private String gameCategory;

    private int igdbCode;

    @ManyToMany
    List<Game> gamesList;
    private GameCategory(){}


    public GameCategory(String name, String gameCategory) {
        super();
        this.setName(name);
        this.gameCategory = gameCategory;
    }


}
