package com.gamehouse.project.services;



import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.GameCategory;
import com.gamehouse.project.models.GameJson;
import com.gamehouse.project.models.GamePlatform;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class APICallService {

//    HttpRequest request = HttpRequest.newBuilder()
//            .uri(URI.create("https://api.igdb.com/v4/games/"))
//            .header("Client-ID", "u069o889u6gzmm9wgbff6n46wduvz4")
//            .header("Authorization", "Bearer 5ib2itgwj5h9bf18ywvthaal9n1nqy")
//            .setHeader("Content-Type", "application/json")
//            //.method("POST", HttpRequest.BodyPublishers.ofString(searchTerm))
//            .build();
    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new GsonBuilder().create();

    public String searchGames(String searchItem) {
        HttpResponse<String> response = null;

        try {

            String searchTerm = "fields name, genres, platforms, age_ratings, summary, cover; search \"" + searchItem + "\";";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.igdb.com/v4/games/"))
                    .header("Client-ID", "u069o889u6gzmm9wgbff6n46wduvz4")
                    .header("Authorization", "Bearer 5ib2itgwj5h9bf18ywvthaal9n1nqy")
                    .setHeader("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(searchTerm))
                    .build();
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response.body();
    };

    public String getIDGBGameByID(Long IDGBCode) {
        HttpResponse<String> response = null;

        try {

            String searchTerm = "fields name, genres, platforms, age_ratings, summary, cover; where id = " + IDGBCode + ";";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.igdb.com/v4/games/"))
                    .header("Client-ID", "u069o889u6gzmm9wgbff6n46wduvz4")
                    .header("Authorization", "Bearer 5ib2itgwj5h9bf18ywvthaal9n1nqy")
                    .setHeader("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(searchTerm))
                    .build();
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return response.body();
    };

    public Game getGamebyIDGBCODE(long IDGBCode) throws Exception {
        String response = getIDGBGameByID(IDGBCode);
        List<GameJson> gameJsons = gson.fromJson(response, new TypeToken<List<GameJson>>(){}.getType());
        //GameJson[] gameJsons = gson.fromJson(response, GameJson[].class);
        //System.out.println(gameJsons.get(0));
        Game tempGame = new Game();
        tempGame.setName(gameJsons.get(0).getName());
        tempGame.setBoxArtURL(getCover(gameJsons.get(0).getCover()));
        tempGame.setGameRating(getRating(gameJsons.get(0).getAge_ratings()));
        tempGame.setGameCategories(getGenre(gameJsons.get(0).getGenres()));
        tempGame.setGameDescription(gameJsons.get(0).getSummary());
        tempGame.setGamePlatforms(getPlatforms(gameJsons.get(0).getPlatforms()));

        //System.out.println(tempGame);
        return tempGame; //return the list of games
    }





    public List<Game> getGamesbyList(String searchTerm) throws Exception {
        String response = searchGames(searchTerm);
        List<GameJson> gameJsons = gson.fromJson(response, new TypeToken<List<GameJson>>(){}.getType());
        List <Game> games = new ArrayList<>();

        for (int x = 0; x<gameJsons.size();x++){
            Game tempGame = new Game();
            tempGame.setName(gameJsons.get(x).getName());
            tempGame.setBoxArtURL(getCover(gameJsons.get(x).getCover()));
            tempGame.setGameRating(getRating(gameJsons.get(x).getAge_ratings()));
            tempGame.setGameCategories(getGenre(gameJsons.get(x).getGenres()));
            tempGame.setGameDescription(gameJsons.get(x).getSummary());
            tempGame.setGamePlatforms(getPlatforms(gameJsons.get(x).getPlatforms()));
            games.add(tempGame);
        }
        return games; //return the list of games
    }

    public List<Game> getGamesLight(String searchTerm) throws Exception {
        String response = searchGames(searchTerm);
        List<GameJson> gameJsons = gson.fromJson(response, new TypeToken<List<GameJson>>(){}.getType());
        List <Game> games = new ArrayList<>();

        for (int x = 0; x<gameJsons.size();x++){
            Game tempGame = new Game();
            tempGame.setName(gameJsons.get(x).getName());
            tempGame.setIGDBCode(gameJsons.get(x).getId());
            tempGame.setBoxArtURL(getCover(gameJsons.get(x).getCover()));
            games.add(tempGame);
        }
        return games; //return the list of games
    }



    public String getCover(int coverID){
        HttpResponse<String> response = null;

        try {

            String searchTerm = "fields url; where id = " + coverID + ";";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.igdb.com/v4/covers/"))
                    .header("Client-ID", "u069o889u6gzmm9wgbff6n46wduvz4")
                    .header("Authorization", "Bearer 5ib2itgwj5h9bf18ywvthaal9n1nqy")
                    .setHeader("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(searchTerm))
                    .build();

            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println(response.body());
        int front = response.body().lastIndexOf("b/" )+2;
        int last = response.body().lastIndexOf("g")+1;
        String url = response.body().substring(front, last);
        String url2 = "https://images.igdb.com/igdb/image/upload/t_cover_big/"+url;
        return url2;
    }


    public String getRating(int[] ratingList){
        HttpResponse<String> response = null;
        String ESRB = "";
        int ratingNumber = 0;
        try {
            for(int x = 0; x< ratingList.length; x++){
                String searchTerm = "fields rating; where id = " + ratingList[x] + ";";
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.igdb.com/v4/age_ratings/"))
                        .header("Client-ID", "u069o889u6gzmm9wgbff6n46wduvz4")
                        .header("Authorization", "Bearer 5ib2itgwj5h9bf18ywvthaal9n1nqy")
                        .setHeader("Content-Type", "application/json")
                        .method("POST", HttpRequest.BodyPublishers.ofString(searchTerm))
                        .build();

                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                int first = response.body().lastIndexOf(":")+2;
                String rating = response.body().substring(first, first+2);
                rating = rating.replaceAll("\n", "");
                ratingNumber = Integer.parseInt(rating);

                switch (ratingNumber){
                    case 6:
                        ESRB = "RP";
                        break;
                    case 7:
                        ESRB = "EC";
                        break;
                    case 8:
                        ESRB = "E";
                        break;
                    case 9:
                        ESRB = "E10";
                        break;
                    case 10:
                        ESRB = "T";
                        break;
                    case 11:
                        ESRB = "M";
                        break;
                    case 12:
                        ESRB = "AO";
                        break;
                }
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ESRB;
    }

    public List<GameCategory> getGenre(int[] genreList){
        HttpResponse<String> response = null;
        List<GameCategory> genres = new ArrayList<>();

        try {
            for(int x = 0; x< genreList.length; x++){
                String searchTerm = "fields name; where id = " + genreList[x] + ";";
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.igdb.com/v4/genres/"))
                        .header("Client-ID", "u069o889u6gzmm9wgbff6n46wduvz4")
                        .header("Authorization", "Bearer 5ib2itgwj5h9bf18ywvthaal9n1nqy")
                        .setHeader("Content-Type", "application/json")
                        .method("POST", HttpRequest.BodyPublishers.ofString(searchTerm))
                        .build();

                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                int first = response.body().lastIndexOf(":")+2;
                int last = response.body().lastIndexOf("}")-2;
                String genre = response.body().substring(first, last);
                genre = genre.replaceAll("\"", "");
                genre = genre.replaceAll("}","");
                genre = genre.replaceAll("\n","");
                GameCategory category = new GameCategory();
                category.setName(genre);
                category.setIgdbCode(genreList[x]);
                genres.add(category);
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return genres;
    }

    public List<GamePlatform> getPlatforms(int[] platformList){
        HttpResponse<String> response = null;
        List<GamePlatform> gamePlatforms = new ArrayList<>();

        try {
            for(int x = 0; x< platformList.length; x++){
                String searchTerm = "fields name; where id = " + platformList[x] + ";";
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.igdb.com/v4/platforms/"))
                        .header("Client-ID", "u069o889u6gzmm9wgbff6n46wduvz4")
                        .header("Authorization", "Bearer 5ib2itgwj5h9bf18ywvthaal9n1nqy")
                        .setHeader("Content-Type", "application/json")
                        .method("POST", HttpRequest.BodyPublishers.ofString(searchTerm))
                        .build();

                response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                //System.out.println(response.body());
                int first = response.body().lastIndexOf(":")+2;
                int last = response.body().lastIndexOf("}")-2;
                String platform = response.body().substring(first, last);
                platform = platform.replaceAll("\"", "");
                platform = platform.replaceAll("}","");
                platform = platform.replaceAll("\n","");
                //System.out.println(platform);
                GamePlatform gamePlatform = new GamePlatform();
                gamePlatform.setName(platform);
                gamePlatform.setIgdbCode(platformList[x]);
                gamePlatforms.add(gamePlatform);
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return gamePlatforms;
    }



}