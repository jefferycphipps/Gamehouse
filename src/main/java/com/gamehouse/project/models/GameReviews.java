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
    private Game game;

    private String gameName;

    private String gameReview;

    private String username;

    private long igdbCode;


    /* CONSTRUCTORS */

//    public GameReviews(String gameReview) {
//        this.gameReview = gameReview;
//    }


    public GameReviews(String gameName, String gameReview, String username, long igdbCode) {
        this.gameName = gameName;
        this.gameReview = gameReview;
        this.username = username;
        this.igdbCode = igdbCode;
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
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

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
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


//    @Override
//    public String toString() {
//        return "GameReviews{" +
//                "id=" + id +
//                ", user=" + user +
//                ", game=" + game +
//                ", gameName='" + gameName + '\'' +
//                ", gameReview='" + gameReview + '\'' +
//                ", username='" + username + '\'' +
//                ", igdbCode=" + igdbCode +
//                '}';
//    }


    @Override
    public String toString() {
        return "GameReviews{" +
                "gameName='" + gameName + '\'' +
                ", gameReview='" + gameReview + '\'' +
                ", username='" + username + '\'' +
                ", igdbCode=" + igdbCode +
                '}';
    }
}
