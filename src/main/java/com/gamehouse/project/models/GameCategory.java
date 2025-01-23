package com.gamehouse.project.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;
import java.util.Objects;

@Entity
public class GameCategory extends AbstractEntity {


    private long igdbCode;

    @ManyToMany(mappedBy = "gameCategories",
    cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    List<Game> gamesList;

    @ManyToMany(mappedBy = "gameCategories")
    private List<Game> gamesListCategories;

    public GameCategory(){}


    public GameCategory(String name, long igdbCode) {
        super();
        this.setName(name);
        this.igdbCode = igdbCode;
    }



    public long getIgdbCode() {
        return igdbCode;
    }

    public void setIgdbCode(long igdbCode) {
        this.igdbCode = igdbCode;
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    public void setGamesList(List<Game> gamesList) {
        this.gamesList = gamesList;
    }


    @Override
    public String toString() {
        return "GameCategory{" +
                "gameCategory='" + getName() + '\'' +
                ", igdbCode=" + igdbCode +
                '}';
    }

    // CT Note: Added to see if removes instances of duplicates
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GameCategory category = (GameCategory) o;
        return igdbCode == category.igdbCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), igdbCode);
    }
}
