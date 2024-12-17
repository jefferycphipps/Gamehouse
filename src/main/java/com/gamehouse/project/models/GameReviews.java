package com.gamehouse.project.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class GameReviews extends AbstractEntity{




    @ManyToMany
    List<Game> gamesList;


    public GameReviews(String name, String gameReview) {
        super();
        this.setName(name);
    }

    public GameReviews() {}

}
