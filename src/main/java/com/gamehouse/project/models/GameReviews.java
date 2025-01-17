package com.gamehouse.project.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Optional;

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
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User username;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private String gameReview;

    private long igdbCode;



    /* CONSTRUCTORS */

    public GameReviews(String gameReview, long igdbCode, User username, Game game) {
        this.gameReview = gameReview;
        this.igdbCode = igdbCode;
        this.username = username;
        this.game = game;
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

    public long getIgdbCode() {
        return igdbCode;
    }

    public void setIgdbCode(long igdbCode) {
        this.igdbCode = igdbCode;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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

    @Override
    public String toString() {
        return "GameReviews{" +
                "username=" + username +
                ", game=" + game +
                ", gameReview='" + gameReview + '\'' +
                ", igdbCode=" + igdbCode +
                '}';
    }
}
