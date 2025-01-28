package com.gamehouse.project.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Game extends AbstractEntity{

    private String gameRating;

    private long igdbCode;

    @Column(length = 1000)
    private String gameDescription;

    private String boxArtURL;

    @OneToMany (cascade = CascadeType.ALL,
            mappedBy = "game")
    private List<GameReviews> gameReviews;

    @ManyToMany
    private List<GameCategory> gameCategories;

    @ManyToMany
    private List<GamePlatform> gamePlatforms;

    @OneToOne (cascade = CascadeType.ALL,
        mappedBy = "game")
    private WishlistGame wishlistGame;

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

    public Game(String name, long igdbCode, String boxArtURL){
        super();
        this.setName(name);
        this.igdbCode = igdbCode;
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

    public long getIgdbCode() {
        return igdbCode;
    }

    public void setIgdbCode(long igdbCode) {
        this.igdbCode = igdbCode;
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

    public WishlistGame getWishlistGame() {
        return wishlistGame;
    }

    public void setWishlistGame(WishlistGame wishlistGame) {
        this.wishlistGame = wishlistGame;
    }



    @Override
    public String toString() {
        return "Game{" +
                "gameRating='" + gameRating + '\'' +
                ", igdbCode=" + igdbCode +
                ", gameDescription='" + gameDescription + '\'' +
                ", boxArtURL='" + boxArtURL + '\'' +
                ", gameReviews=" + gameReviews +
                ", gameCategories=" + gameCategories +
                ", gamePlatforms=" + gamePlatforms +
                '}';
    }
}
