package com.gamehouse.project.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class GamePlatform extends AbstractEntity{


    private int igdbCode;

<<<<<<< HEAD
    @ManyToMany(mappedBy = "gamePlatforms",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    List<Game> gamesList;
=======
    @ManyToMany(mappedBy = "gamePlatforms")
    private List<Game> gamesList;
>>>>>>> 98228a0dad96f3a5c95a9bc953a2555fac586f8e

    public GamePlatform(){}


    public GamePlatform(String gameName, int igdbCode) {
        super();
        this.setName(gameName);
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
        return "GamePlatform{" +
                "platform=" + getName() +
                " igdbCode=" + igdbCode +
                " }";
    }
}
