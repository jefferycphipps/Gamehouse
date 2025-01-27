package com.gamehouse.project.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class GameReviews {

//    @ManyToMany(mappedBy = "gameReviews",
//            cascade = {
//                    CascadeType.MERGE,
//                    CascadeType.PERSIST
//            })
//    List<Game> gamesList;
//
//    @ManyToMany(mappedBy = "gameReviews")
//    private List<Game> gamesListReviews;


    @Id
    @GeneratedValue
    private int id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // still need to configure mapping
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game gameReviewed;
    
    private String gameReview;

    private long igdbCode;

    private String username;



    /* CONSTRUCTORS */

//    public GameReviews(String gameReview) {
//        this.gameReview = gameReview;
//    }


    public GameReviews(String gameReview, long igdbCode, String username) {
        this.gameReview = gameReview;
        this.igdbCode = igdbCode;
        this.username = username;
    }

    public GameReviews() {}



    /* GETTERS & SETTERS */

    public int getId() {
        return id;
    }

    public String getGameReview() {
        return gameReview;
    }

    public void setGameReview(String gameReview) {
        this.gameReview = gameReview;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Game getGameReviewed() {
        return gameReviewed;
    }

    public void setGameReviewed(Game gameReviewed) {
        this.gameReviewed = gameReviewed;
    }

    public long getIgdbCode() {
        return igdbCode;
    }

    public void setIgdbCode(long igdbCode) {
        this.igdbCode = igdbCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameReviews that = (GameReviews) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
