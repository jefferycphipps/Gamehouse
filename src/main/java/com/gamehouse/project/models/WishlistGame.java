package com.gamehouse.project.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class WishlistGame {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private String username;
    private String gameName;
    private long igdbCode;

    public WishlistGame(String userName, String gameName, long igdbCode) {
        this.username = userName;
        this.gameName = gameName;
        this.igdbCode = igdbCode;
    }

    public WishlistGame() {}


    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WishlistGame that = (WishlistGame) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "WishlistGame{" +
                "id=" + id +
                ", user=" + user +
                ", username='" + username + '\'' +
                ", gameName='" + gameName + '\'' +
                ", igdbCode=" + igdbCode +
                '}';
    }
}
