package com.gamehouse.project.controllers;


import com.gamehouse.project.models.*;
import com.gamehouse.project.models.data.*;
import com.gamehouse.project.services.APICallService;
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

    // Test to see game description & size based on game igdbCode
    @PostMapping("/description")
    public String getGameDescription(@RequestBody long igdbCode) throws Exception {
        APICallService newApiCall  = new APICallService();
        Game getGame = newApiCall.getGamebyIDGBCODE(igdbCode);

//        Game newGameDescription = new Game();
//        newGameDescription.setGameDescription(getGame.getGameDescription());

        return getGame.getGameDescription();
    }


    // Create/Save new game to gameRepository by igdbCode
    @PostMapping("/saveGame")
//    public String newGame(@RequestBody long igdbCode) throws Exception {
//    public Game newGame(@RequestBody long igdbCode) throws Exception {
    public ResponseEntity<Game> newGame(@RequestBody long igdbCode) throws Exception {


        // Uses igdbCode to retrieve game from APICallService
        APICallService newApiCall = new APICallService();
        Game gameByIgdb = newApiCall.getGamebyIDGBCODE(igdbCode);


        // Save all categories into the repo
        for (GameCategory category : gameByIgdb.getGameCategories()) {

//            gameCategoryRepository.findByigdbCode(category.getIgdbCode()).orElseGet(() -> gameCategoryRepository.save(category));

            Optional<GameCategory> gameCategory = gameCategoryRepository.findByigdbCode(category.getIgdbCode());

            if (gameCategory.isPresent()) {
                GameCategory updateCategory = gameCategory.get();
                updateCategory.setName(category.getName());
                updateCategory.setIgdbCode(category.getIgdbCode());
                gameCategoryRepository.save(updateCategory);


//                gameCategoryRepository.save(category);
                

            }
            else {
                gameCategoryRepository.save(category);
            }
        }
//        gameCategoryRepository.saveAll(gameByIgdb.getGameCategories());



        //save all platforms into the repo
//        Optional <GamePlatform> gamePlatform = gamePlatformRepository.findByigdbCode(newGameIgdbCode);
//
//        if (!gamePlatform.isPresent()) {
//            GamePlatform newGamePlatform = new GamePlatform();
//
//            for (GamePlatform platform : game.getGamePlatforms()) {
//                newGamePlatform.setName(platform.toString());
//                newGamePlatform.setIgdbCode(newGameIgdbCode);
//
                gamePlatformRepository.saveAll(gameByIgdb.getGamePlatforms());
//            }
//        }


        // Saves game to gameRepository
        gameRepository.save(gameByIgdb);

        return ResponseEntity.status(HttpStatus.CREATED).body(gameByIgdb);
//        return newGame;
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


    // Get List of Game Reviews by game name
    @GetMapping("/getReviews")
    public List<GameReviews> getGameReviewByIgdb (@RequestBody long igdbCode) {

        // CT Note: Unable to use igdbCode because field in Game is IGDBCode - but repository requires Camel case for igdbCode field
        Optional<GameReviews> reviewByIgdbCode = gameReviewsRepository.findByIgdbCode(igdbCode);

        // Check to see if the game is in gameRepository
//        Optional<Game> currentGameByIgbd = gameRepository.findByIgdbCode(igdbCode);

        if (reviewByIgdbCode.isPresent()) {
            List<GameReviews> gameReviewsByIgdbCode = (List<GameReviews>) reviewByIgdbCode.get();
            return (gameReviewsByIgdbCode);

        } else {
            return null;
        }
    }


    // Saving New Game Reviews based on game's Igdb code
    @PostMapping("/saveReview")
    public ResponseEntity<GameReviews> saveGameReview(@RequestBody long igdbCode, @RequestBody String gameReview, @RequestBody User username) throws Exception {

        GameReviews newGameReview = new GameReviews();

        Optional<User> newUser = userRepository.findByUsername(username.getUsername());

        if (newUser.isPresent()) {
            newGameReview.setUsername(newUser.get());
        }

        // Search gameRepository to find game based on igdbCode
        Optional<Game> newGame = gameRepository.findByIgdbCode(igdbCode);

        if (newGame.isPresent()) {
//            Game newGame = game.get();
            newGameReview.setGame(newGame.get());

        } else {

            // Uses igdbCode to retrieve game from APICallService if NOT saved in gameRepository
            APICallService newApiCall = new APICallService();
            Game addNewGame = newApiCall.getGamebyIDGBCODE(igdbCode);
            gameRepository.save(addNewGame);

            Game addedGame = gameRepository.findByIgdbCode(igdbCode).get();
            newGameReview.setGame(addedGame);

        }

        newGameReview.setIgdbCode(igdbCode);
        newGameReview.setGameReview(gameReview);
        gameReviewsRepository.save(newGameReview);

        return ResponseEntity.status(HttpStatus.CREATED).body(newGameReview);
    }

}
