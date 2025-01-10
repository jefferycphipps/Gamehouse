package com.gamehouse.project.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class GameReviews extends AbstractEntity{




    @ManyToMany(mappedBy = "gameReviews",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    List<Game> gamesList;
    @ManyToMany(mappedBy = "gameReviews")
    private List<Game> gamesListReviews;


    public GameReviews(String name, String gameReview) {
        super();
        this.setName(name);
    }

    public GameReviews() {}

}
