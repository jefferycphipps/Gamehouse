package com.gamehouse.project.models;

import jakarta.persistence.*;

import java.util.Objects;

public class OwnedGame {

    /* FIELDS */

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private String username;

    @Column(nullable = false)
    private String gameName;

    @Column(nullable = false)
    private long igdbCode;


    /* CONSTRUCTORS */

    public OwnedGame(String username, String gameName, long igdbCode) {
        this.username = username;
        this.gameName = gameName;
        this.igdbCode = igdbCode;
    }

    public OwnedGame() {}


    /* GETTERS & SETTERS */

    public int getId() {
        return id;
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

    public long getIgdbCode() {
        return igdbCode;
    }

    public void setIgdbCode(long igdbCode) {
        this.igdbCode = igdbCode;
    }


    /* OVERRIDE METHODS */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnedGame ownedGame = (OwnedGame) o;
        return id == ownedGame.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "OwnedGame{" +
                "id=" + id +
                ", user=" + user +
                ", game=" + game +
                ", username='" + username + '\'' +
                ", gameName='" + gameName + '\'' +
                ", igdbCode=" + igdbCode +
                '}';
    }
}
