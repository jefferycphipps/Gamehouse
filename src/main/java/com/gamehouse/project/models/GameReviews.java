package com.gamehouse.project.models;

import jakarta.persistence.*;

import java.util.List;
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
    private User usernameReviewer;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game gameReviewed;

    private String gameReview;



    /* CONSTRUCTORS */

    public GameReviews(String gameReview) {
        this.gameReview = gameReview;
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

    public User getUsernameReviewer() {
        return usernameReviewer;
    }

    public Game getGameReviewed() {
        return gameReviewed;
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
