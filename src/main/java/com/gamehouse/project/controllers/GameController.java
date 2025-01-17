package com.gamehouse.project.controllers;


import com.gamehouse.project.models.*;
import com.gamehouse.project.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    private UserRepository userRepository;


    //Create new game
    @PostMapping("/saveGame")
    public ResponseEntity<Game> newGame(@RequestBody Game game) throws Exception {

        //have to save all categories into the repo
        long newGameIgdbCode = game.getIGDBCode();

        Optional <GameCategory> gameCategory = gameCategoryRepository.findByigdbCode(newGameIgdbCode);

        if (!gameCategory.isPresent()) {

            GameCategory newGameCategory = new GameCategory();

            for (GameCategory category : game.getGameCategories()) {
                newGameCategory.setName(category.toString());
                newGameCategory.setIgdbCode(newGameIgdbCode);

                gameCategoryRepository.save(newGameCategory);
            }
        }


        //save all platforms into the repo
        Optional <GamePlatform> gamePlatform = gamePlatformRepository.findByigdbCode(newGameIgdbCode);

        if (!gamePlatform.isPresent()) {
            GamePlatform newGamePlatform = new GamePlatform();

            for (GamePlatform platform : game.getGamePlatforms()) {
                newGamePlatform.setName(platform.toString());
                newGamePlatform.setIgdbCode(newGameIgdbCode);

                gamePlatformRepository.save(newGamePlatform);
            }
        }


        //save new reviews

        Game newGame = gameRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);

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


    // Get List of Game Reviews by igdb code
    @GetMapping("/{igdbCode}")
    public List<GameReviews> getGameReviewByIgdbCode(@PathVariable long igdbCode) {

        Optional<GameReviews> reviewByIgdbCode = gameReviewsRepository.findByIgdbCode(igdbCode);

        if (reviewByIgdbCode.isPresent()) {
            List<GameReviews> gameReviewsByIgdbCode = (List<GameReviews>) reviewByIgdbCode.get();
            return (gameReviewsByIgdbCode);

        } else {
            return null;
        }
    }


    // Saving New Game Reviews based on Igdb code
    @PostMapping("/{igdbCode}")
    public ResponseEntity<GameReviews> saveGameReview(@PathVariable long igdbCode, @RequestBody String gameReview, @RequestBody User username) {

        GameReviews newGameReview = new GameReviews();

        Optional<User> newUser = userRepository.findByUsername(username.getUsername());

        if (newUser.isPresent()) {
            newGameReview.setUsername(username);
        }

        Optional<Game> game = gameRepository.findByIgdbCode(igdbCode);

        if (game.isPresent()) {
            Game newGame = game.get();
            newGameReview.setGame(newGame);
        }

        newGameReview.setGameReview(gameReview);
        gameReviewsRepository.save(newGameReview);

        return ResponseEntity.ok(newGameReview);
    }

}
