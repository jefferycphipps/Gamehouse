package com.gamehouse.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class GameCategory extends AbstractEntity {

    //private String gameCategory;

    private int igdbCode;

    @ManyToMany
    List<Game> gamesList;

    public GameCategory(){}


    public GameCategory(String name, int igdbCode) {
        super();
        this.setName(name);
        //this.gameCategory = gameCategory;
        this.igdbCode = igdbCode;
    }



    public int getIgdbCode() {
        return igdbCode;
    }

    public void setIgdbCode(int igdbCode) {
        this.igdbCode = igdbCode;
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList = gamesList;
    }


    @Override
    public String toString() {
        return "GameCategory{" +
                "gameCategory='" + getName() + '\'' +
                ", igdbCode=" + igdbCode +
                '}';
    }
}
