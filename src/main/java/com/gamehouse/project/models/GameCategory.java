package com.gamehouse.project.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class GameCategory extends AbstractEntity {


    private int igdbCode;

<<<<<<< HEAD
    @ManyToMany(mappedBy = "gameCategories",
    cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    List<Game> gamesList;
=======
    @ManyToMany(mappedBy = "gameCategories")
    private List<Game> gamesList;
>>>>>>> 98228a0dad96f3a5c95a9bc953a2555fac586f8e

    public GameCategory(){}


    public GameCategory(String name, int igdbCode) {
        super();
        this.setName(name);
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
