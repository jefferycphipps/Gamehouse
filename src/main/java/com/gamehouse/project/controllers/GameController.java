package com.gamehouse.project.controllers;


import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.GameCategory;
import com.gamehouse.project.models.data.GameCategoryRepository;
import com.gamehouse.project.models.data.GamePlatformRepository;
import com.gamehouse.project.models.data.GameRepository;
import com.gamehouse.project.models.data.GameReviewsRepository;
import com.gamehouse.project.services.APICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameCategoryRepository  gameCategoryRepository;

    @Autowired
    private GamePlatformRepository gamePlatformRepository;

    @Autowired
    private GameReviewsRepository gameReviewsRepository;


    //Create new game
    @PostMapping("/saveGame")
    public ResponseEntity<Game> newGame(@RequestBody long igdbCode) throws Exception {

        // Uses igdbCode to retrieve game from APICallService
        APICallService newApiCall = new APICallService();
        Game getGameByIgdb = newApiCall.getGamebyIDGBCODE(igdbCode);

        //have to save all categories into the repo -- STILL need to figure out how to prevent adding duplicates
        gameCategoryRepository.saveAll(getGameByIgdb.getGameCategories());

        //save all platforms into the repo -- STILL need to figure out how to prevent adding duplicates
        gamePlatformRepository.saveAll(getGameByIgdb.getGamePlatforms());


        // Saves Game to gameRepository
        gameRepository.save(getGameByIgdb);

        return ResponseEntity.status(HttpStatus.CREATED).body(getGameByIgdb);
    }


    @PostMapping("/saveGames")
    public ResponseEntity<?> newGames(@RequestBody List<Game> games) throws URISyntaxException{
        for (int x = 0; x< games.size(); x++){
            Game newGame = gameRepository.save(games.get(x));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("saved list");

    }


    @GetMapping("/games")
    public List<Game> getGames() {
        return (List<Game>) gameRepository.findAll();
    }


    @GetMapping("/{id}")
    public Game getGame(@PathVariable int id) {
        return gameRepository.findById(id).orElseThrow(RuntimeException::new);
    }


    //Update game...will probably need more code to edit this.
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game){
        Game currentGame = gameRepository.findById(id).orElseThrow(RuntimeException::new);
        currentGame.setName(game.getName());
        currentGame = gameRepository.save(game);
        return ResponseEntity.ok(currentGame);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEvent(@PathVariable int id) {
        gameRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
