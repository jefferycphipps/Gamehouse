package com.gamehouse.project.models;

public class Age_Ratings {
    int id;
    int rating;

    public Age_Ratings(){

    }

    public Age_Ratings(int id, int rating) {
        this.id = id;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Age_Ratings{" +
                "id=" + id +
                ", rating=" + rating +
                '}';
    }
}
