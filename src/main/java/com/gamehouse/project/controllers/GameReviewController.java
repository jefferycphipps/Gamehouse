package com.gamehouse.project.controllers;

import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.GameReviews;
import com.gamehouse.project.models.data.GameRepository;
import com.gamehouse.project.models.data.GameReviewsRepository;
import com.gamehouse.project.models.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
//    @PostMapping("/save")
//    public ResponseEntity<String> saveGameReview (@RequestBody long igdbCode, String username, String gameReview) {
//
//        // Search gameRepository to find game based on igdbCode
//
//
//    }



    // Get Reviews by igdbCode
    @GetMapping("/getReviewsIgdb")
    public List<GameReviews> getReviewsByIgdb(@RequestBody long igdbCode) {
        return (List<GameReviews>) gameReviewsRepository.findByIgdbCode(igdbCode).orElseThrow(RuntimeException::new);
    }


    // Get Reviews by Username


}
