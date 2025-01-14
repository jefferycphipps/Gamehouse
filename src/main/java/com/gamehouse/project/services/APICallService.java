package com.gamehouse.project.services;



import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamehouse.project.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;


@Service
public class APICallService {


    ObjectMapper objectMapper = new ObjectMapper();
    Gson gson = new GsonBuilder().create();

    public String searchGames(String searchItem) {
        HttpResponse<String> response = null;
        //System.out.println(searchItem);
        try {

            String searchTerm = "fields name, genres, platforms, age_ratings.rating, summary, cover.url; search \"" + searchItem + "\";";
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
        System.out.println(response.body());
        return response.body();
    }

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
        Game tempGame = new Game();
        tempGame.setName(gameJsons.get(0).getName());
        tempGame.setBoxArtURL(getCover(gameJsons.get(0).getCover()));
        //tempGame.setGameRating(getRating(gameJsons.get(0).getAge_ratings()));
        tempGame.setGameCategories(getGenre(gameJsons.get(0).getGenres()));
        tempGame.setGameDescription(gameJsons.get(0).getSummary());
        tempGame.setGamePlatforms(getPlatforms(gameJsons.get(0).getPlatforms()));
        tempGame.setIGDBCode(gameJsons.get(0).getId());

        //System.out.println(tempGame);
        return tempGame; //return the list of games
    }





    //Do we need to get the games from the api then convert to game in the db? Only once.
    public List<Game> getGamesbyList(String searchTerm) throws Exception {
        String response = searchGames(searchTerm);
        List<GameJson> gameJsons = gson.fromJson(response, new TypeToken<List<GameJson>>(){}.getType());
        List <Game> games = new ArrayList<>();

        for (int x = 0; x<gameJsons.size();x++){
            Game tempGame = new Game();
            tempGame.setName(gameJsons.get(x).getName());
            tempGame.setBoxArtURL(getCover(gameJsons.get(x).getCover()));
            //List<Age_Ratings> ratings = gameJsons.get(x).getAge_ratings(); game ratings needs to be dealt with. there are multiple ratings per game.

            tempGame.setGameCategories(getGenre(gameJsons.get(x).getGenres()));
            tempGame.setGameDescription(gameJsons.get(x).getSummary());
            tempGame.setGamePlatforms(getPlatforms(gameJsons.get(x).getPlatforms()));
            games.add(tempGame);
        }
        return games; //return the list of games
    }

    public List<GameLight> getGamesLight(String searchTerm) throws Exception {
        String response = searchGames(searchTerm);
        System.out.println(response);
        List<GameJson> gameJsons = gson.fromJson(response, new TypeToken<List<GameJson>>(){}.getType());
        List <GameLight> games = new ArrayList<>();

        for (int x = 0; x<gameJsons.size();x++) {
            GameLight tempGame = new GameLight();
            tempGame.setName(gameJsons.get(x).getName());
            tempGame.setIGDBCode(gameJsons.get(x).getId());
            tempGame.setBoxArtUrl(getCover(gameJsons.get(x).getCover()));
            games.add(tempGame);
            System.out.println(tempGame);
        }
        return games; //return the list of games
    }

    public List<GameCategory> saveGameGenres() throws Exception{

        HttpResponse<String> response = null;

        try {

            String searchTerm = "fields name; limit 50;";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.igdb.com/v4/genres/"))
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
        List<GameCategory> gameCategory = gson.fromJson(response.body(), new TypeToken<List<GameCategory>>(){}.getType());
        List<GameCategory> tempList = new ArrayList<>();
        for (int x = 0; x<gameCategory.size();x++){
            //System.out.println(gameCategory.get(x));
            GameCategory tempGameCategory = new GameCategory();
            tempGameCategory.setName(gameCategory.get(x).getName());
            tempGameCategory.setIgdbCode(gameCategory.get(x).getId());
            tempList.add(tempGameCategory);
        }
        return tempList;

    }

    public List<GamePlatform> saveGamePlatforms() throws Exception{

        HttpResponse<String> response = null;

        try {

            String searchTerm = "fields name; limit 500;";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.igdb.com/v4/platforms/"))
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
        List<GamePlatform> gamePlatform = gson.fromJson(response.body(), new TypeToken<List<GamePlatform>>(){}.getType());
        List<GamePlatform> tempList = new ArrayList<>();
        for (int x = 0; x< gamePlatform.size(); x++){
            //System.out.println(gameCategory.get(x));
            GamePlatform tempGamePlatform = new GamePlatform();
            tempGamePlatform.setName(gamePlatform.get(x).getName());
            tempGamePlatform.setIgdbCode(gamePlatform.get(x).getId());
            tempList.add(tempGamePlatform);
        }
        return tempList;

    }

    public String getCover(Cover cover){
        if (cover==null){
            return "dummy";
        }
        String url = cover.getUrl();
        int front = url.lastIndexOf("b/" )+2;
        int last = url.lastIndexOf("g")+1;
        String url1 = url.substring(front, last);
        String url2 = "https://images.igdb.com/igdb/image/upload/t_cover_big/"+url1;
        return url2;
    }

    public String getGameRating(Age_Ratings age_ratings){
        int rating = age_ratings.getRating();
        String ESRB = "";
        switch (rating){
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
        return ESRB;
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