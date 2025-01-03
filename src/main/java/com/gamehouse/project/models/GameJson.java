package com.gamehouse.project.models;

import java.util.Arrays;
import java.util.Objects;

public class GameJson {
     String name;
     long id;
     int[] age_ratings;
     int category;
     int cover;
     String boxartURL;
     int[] platforms;
     String summary;

     public GameJson(){}

     public GameJson(String name, long id, int[] age_ratings, int category, int cover, int[] platforms, String summary) {
          this.name = name;
          this.id = id;
          this.age_ratings = age_ratings;
          this.category = category;
          this.cover = cover;
          this.platforms = platforms;
          this.summary = summary;
     }


     public String getName() {
          return name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public long getId() {
          return id;
     }

     public void setId(long id) {
          this.id = id;
     }

     public int[] getAge_ratings() {
          return age_ratings;
     }

     public void setAge_ratings(int[] age_ratings) {
          this.age_ratings = age_ratings;
     }

     public int getCategory() {
          return category;
     }

     public void setCategory(int category) {
          this.category = category;
     }

     public int[] getPlatforms() {
          return platforms;
     }

     public void setPlatforms(int[] platforms) {
          this.platforms = platforms;
     }

     public int getCover() {
          return cover;
     }

     public void setCover(int cover) {
          this.cover = cover;
     }

     public String getSummary() {
          return summary;
     }

     public void setSummary(String summary) {
          this.summary = summary;
     }


     public String getBoxartURL() {
          return boxartURL;
     }

     public void setBoxartURL(String boxartURL) {
          this.boxartURL = boxartURL;
     }

     @Override
     public String toString() {
          return "GameJson {" + "\n" +
                  "name='" + name + '\'' + ", \n" +
                  "id=" + id + ", \n" +
                  "age_ratings=" + Arrays.toString(age_ratings) + ", \n" +
                  "category=" + category + ", \n" +
                  "cover=" + cover + ", \n" +
                  "platforms=" + Arrays.toString(platforms) + ", \n" +
                  "summary='" + summary + '\'' + "\n" +
                  '}' + "\n";
     }

     @Override
     public boolean equals(Object o) {
          if (this == o) return true;
          if (o == null || getClass() != o.getClass()) return false;
          GameJson gameJson = (GameJson) o;
          return id == gameJson.id;
     }

     @Override
     public int hashCode() {
          return Objects.hashCode(id);
     }
}
