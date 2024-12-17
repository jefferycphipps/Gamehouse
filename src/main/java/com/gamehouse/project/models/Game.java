package com.gamehouse.project.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Game extends AbstractEntity{

    private String gameRating;

    private String gameDescription;

    private Image boxArt;

    @ManyToMany
    private List<GameReviews> gameReviews;

    //Needs to be changed to list of game categories.
    @ManyToMany
    private List<GameCategory> gameCategories;

    @ManyToMany
    private List<GamePlatform> gamePlatforms;

    public Game(){}


    public Game(String name, String gameRating, String gameDescription, List<GameReviews> gameReviews, List<GameCategory> gameCategories, List<GamePlatform> gamePlatforms) {
        super();
        this.setName(name);
        this.gameRating = gameRating;
        this.gameDescription = gameDescription;
        this.boxArt = boxArt;
        this.gameReviews = gameReviews;
        this.gameCategories = gameCategories;
        this.gamePlatforms = gamePlatforms;
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

    public Image getBoxArt() {
        return boxArt;
    }

    public void setBoxArt(Image boxArt) {
        this.boxArt = boxArt;
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
}
