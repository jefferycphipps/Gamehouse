package com.gamehouse.project.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
public class GameReviews extends AbstractEntity{




<<<<<<< HEAD
    @ManyToMany(mappedBy = "gameReviews",
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.PERSIST
            })
    List<Game> gamesList;
=======
    @ManyToMany(mappedBy = "gameReviews")
    private List<Game> gamesList;
>>>>>>> 98228a0dad96f3a5c95a9bc953a2555fac586f8e


    public GameReviews(String name, String gameReview) {
        super();
        this.setName(name);
    }

    public GameReviews() {}

}
