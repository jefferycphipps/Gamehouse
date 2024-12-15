package com.gamehouse.project.models;

import jakarta.persistence.Entity;

@Entity
public class GameReviews extends AbstractEntity{
    
    private String gameReview;

    public GameReviews(String gameReview) {
        this.gameReview = gameReview;
    }
}
