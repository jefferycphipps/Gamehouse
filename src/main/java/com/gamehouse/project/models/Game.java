package com.gamehouse.project.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Game extends AbstractEntity{

    private String gameRating;

    private long IGDBCode;

    private String gameDescription;

    private String boxArtURL;

    @OneToMany (mappedBy = "game")
    private List<GameReviews> gameReviews;

    //Needs to be changed to list of game categories.
    @ManyToMany
    private List<GameCategory> gameCategories;

    @ManyToMany
    private List<GamePlatform> gamePlatforms;

    public Game(){}


    public Game(String name, String gameRating, String gameDescription, List<GameReviews> gameReviews, List<GameCategory> gameCategories, List<GamePlatform> gamePlatforms, String boxartURL) {
        super();
        this.setName(name);
        this.gameRating = gameRating;
        this.gameDescription = gameDescription;
        this.boxArtURL = boxArtURL;
        this.gameReviews = gameReviews;
        this.gameCategories = gameCategories;
        this.gamePlatforms = gamePlatforms;
    }

    public Game(String name, long IGDBCode, String boxArtURL){
        super();
        this.setName(name);
        this.IGDBCode = IGDBCode;
        this.boxArtURL = boxArtURL;
    }

    public String getGameRating() {
        return gameRating;
    }

    public void setGameRating(String gameRating) {
        this.gameRating = gameRating;
    }

    public String getGameDescription() {
        return gameDescription;
    }

    public void setGameDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public String getBoxArtURL() {
        return boxArtURL;
    }

    public void setBoxArtURL(String boxArtURL) {
        this.boxArtURL = boxArtURL;
    }

    public long getIGDBCode() {
        return IGDBCode;
    }

    public void setIGDBCode(long IGDBCode) {
        this.IGDBCode = IGDBCode;
    }

    public List<GameReviews> getGameReviews() {
        return gameReviews;
    }

    public void setGameReviews(List<GameReviews> gameReviews) {
        this.gameReviews = gameReviews;
    }

    public List<GameCategory> getGameCategories() {
        return gameCategories;
    }

    public void setGameCategories(List<GameCategory> gameCategories) {
        this.gameCategories = gameCategories;
    }

    public List<GamePlatform> getGamePlatforms() {
        return gamePlatforms;
    }

    public void setGamePlatforms(List<GamePlatform> gamePlatforms) {
        this.gamePlatforms = gamePlatforms;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameRating='" + gameRating + '\'' +
                ", IGDBCode=" + IGDBCode +
                ", gameDescription='" + gameDescription + '\'' +
                ", boxArtURL='" + boxArtURL + '\'' +
                ", gameReviews=" + gameReviews +
                ", gameCategories=" + gameCategories +
                ", gamePlatforms=" + gamePlatforms +
                '}';
    }
}
