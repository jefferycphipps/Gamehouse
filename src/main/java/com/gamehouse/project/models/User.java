package com.gamehouse.project.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToOne;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;



@Entity
public class User extends AbstractEntity{
    private static final   BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
    private String username;
    private String email;
    private String pwHash;


    @OneToOne
    private Image profileImage;

//    @OneToMany (cascade = CascadeType.ALL,
//        mappedBy = "user")
//    private List<GameReviews> gameReviews = new ArrayList<>();

//    @OneToMany (cascade = CascadeType.ALL)
//    private List<WishlistGame> gameWishlist = new ArrayList<>();
//


    public User(String username, String email, String pwHash) {
        super();

        this.username = username;
        this.email = email;
        this.pwHash = pwHash;

    }

    public User(String username, String email, String pwHash, Image profileImage) {
        super();

        this.username = username;
        this.email = email;
        this.pwHash = pwHash;
        this.profileImage = profileImage;

    }

    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public Image getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Image profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isMatchingPassword(String password) {
        return passwordEncode.matches(password,pwHash);
    }


//    public List<GameReviews> getGameReviews() {
//        return gameReviews;
//    }
//
//    public void setGameReviews(List<GameReviews> gameReviews) {
//        this.gameReviews = gameReviews;
//    }

//    public List<WishlistGame> getGameWishlist() {
//        return gameWishlist;
//    }
//
//    public void setGameWishlist(List<WishlistGame> gameWishlist) {
//        this.gameWishlist = gameWishlist;
//    }
}

