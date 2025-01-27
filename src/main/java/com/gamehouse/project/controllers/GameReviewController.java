package com.gamehouse.project.controllers;

import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.GameReviews;
import com.gamehouse.project.models.User;
import com.gamehouse.project.models.data.GameRepository;
import com.gamehouse.project.models.data.GameReviewsRepository;
import com.gamehouse.project.models.data.UserRepository;
import com.gamehouse.project.models.dto.GameReviewsDTO;
import com.gamehouse.project.services.APICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class GameReviewController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameReviewsRepository gameReviewsRepository;

    @Autowired
    private UserRepository userRepository;


    // Save Game Reviews
    @PostMapping("/save")
    public ResponseEntity<String> saveGameReview (@RequestBody GameReviewsDTO gameReviewsDTO) throws Exception {

        // Create new Game Review object
        GameReviews newGameReview = new GameReviews();


        // Search gameRepository to find game based on igdbCode
        Optional<Game> getGame = gameRepository.findByIgdbCode(gameReviewsDTO.getIgdbCode());

        if (getGame.isPresent()) {
            newGameReview.setGame(getGame.get());
            newGameReview.setGameName(getGame.get().getName());

        } else {

            // Uses igdbCode to retrieve game from APICallService if NOT saved in gameRepository
            APICallService newApiCall = new APICallService();
            Game addNewGame = newApiCall.getGamebyIDGBCODE(gameReviewsDTO.getIgdbCode());
            gameRepository.save(addNewGame);

            // THEN, Search gameRepository to find game based on igdbCode
            Game gameAdded = gameRepository.findByIgdbCode(gameReviewsDTO.getIgdbCode()).get();
            newGameReview.setGame(gameAdded);
            newGameReview.setGameName(gameAdded.getName());
        }


        // Search UserRepository to find User based on username
        User getUser = userRepository.findByUsername(gameReviewsDTO.getUsername());

        newGameReview.setUser(getUser);
        newGameReview.setUsername(gameReviewsDTO.getUsername());


        // Setting igdbCode & game review to save in gameReviewRepository
        newGameReview.setIgdbCode(gameReviewsDTO.getIgdbCode());
        newGameReview.setGameReview(gameReviewsDTO.getGameReview());

        gameReviewsRepository.save(newGameReview);

        return ResponseEntity.status(HttpStatus.CREATED).body("Game Review saved");
    }



    // Get Reviews by Game igdbCode
    @GetMapping("/getReviewsIgdb")
    public List<GameReviews> getReviewsByIgdb(@RequestBody long igdbCode) {

        List<GameReviews> reviewsList = new ArrayList<>();

        List<GameReviews> reviewsListByIgdb = gameReviewsRepository.findAllByIgdbCode(igdbCode);


        for (int i = 0; i < reviewsListByIgdb.size(); i++) {
            GameReviews gameReview = new GameReviews();

//            gameReview.setGame(reviewsListByIgdb.get(i).getGame());
//            gameReview.setUser(reviewsListByIgdb.get(i).getUser());
            gameReview.setGameName(reviewsListByIgdb.get(i).getGameName());
            gameReview.setGameReview(reviewsListByIgdb.get(i).getGameReview());
            gameReview.setIgdbCode(reviewsListByIgdb.get(i).getIgdbCode());
            gameReview.setUsername(reviewsListByIgdb.get(i).getUsername());

            reviewsList.add(gameReview);
        }

        return reviewsList;
    }


    // Get Reviews by Username





}
