//package com.gamehouse.project.models;
//
//import jakarta.persistence.*;
//
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//@Entity
//public class Wishlist {
//    @Id
//    @GeneratedValue (strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @OneToOne (mappedBy = "wishlist")
//    private User user;
//
//    @ManyToMany
//    private Set<Game> games = new HashSet<>();
//
//    public Wishlist(User user, Set<Game> games) {
//        this.user = user;
//        this.games = games;
//    }
//
//    public Wishlist(){
//
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Set<Game> getGames() {
//        return games;
//    }
//
//    public void setGames(Set<Game> games) {
//        this.games = games;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Wishlist wishlist = (Wishlist) o;
//        return id == wishlist.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hashCode(id);
//    }
//
//    @Override
//    public String toString() {
//        return "Wishlist{" +
//                "id=" + id +
//                ", user=" + user +
//                ", games=" + games +
//                '}';
//    }
//}
