package com.gamehouse.project.models;

public class Game extends AbstractEntity{

    private String gameRating;

    private String gameDescription;

    private Image boxArt;

    private GameReviews gameReviews;

    private GameCategory gameCategory;

    private GamePlatforms gamePlatforms;

    public Game(){}


    public Game(String name, String gameRating, String gameDescription, Image boxArt, GameReviews gameReviews, GameCategory gameCategory, GamePlatforms gamePlatforms) {
        super();
        this.setName(name);
        this.gameRating = gameRating;
        this.gameDescription = gameDescription;
        this.boxArt = boxArt;
        this.gameReviews = gameReviews;
        this.gameCategory = gameCategory;
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

    public GameReviews getGameReviews() {
        return gameReviews;
    }

    public void setGameReviews(GameReviews gameReviews) {
        this.gameReviews = gameReviews;
    }

    public GameCategory getGameCategory() {
        return gameCategory;
    }

    public void setGameCategory(GameCategory gameCategory) {
        this.gameCategory = gameCategory;
    }

    public GamePlatforms getGamePlatforms() {
        return gamePlatforms;
    }

    public void setGamePlatforms(GamePlatforms gamePlatforms) {
        this.gamePlatforms = gamePlatforms;
    }
}
