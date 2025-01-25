package com.gamehouse.project.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.Objects;

@Entity
//public class GameCategory {
public class GameCategory extends AbstractEntity {

    // CT Note: Comment out lines 14-19 if extends AbstractEntity
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @NotBlank(message = "Name is required")
//    private String name;



//    @Column(unique = true, nullable = false)
    private long igdbCode;

    @ManyToMany(mappedBy = "gameCategories",
    cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    private List<Game> gamesList;

    @ManyToMany(mappedBy = "gameCategories")
    private List<Game> gamesListCategories;

    public GameCategory(){}


    public GameCategory(String name, long igdbCode) {
//        super();
        this.setName(name);
        this.igdbCode = igdbCode;
    }


    // CT Note: Comment out lines 45-53 if extends AbstractEntity
//    public int getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }


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

    // CT Note: Added to see if removes instances of duplicates by making igdbCode unique - Comment out if extends AbstractEntity
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        GameCategory category = (GameCategory) o;
//        return igdbCode == category.igdbCode;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), igdbCode);
//    }
}
