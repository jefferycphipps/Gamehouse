package com.gamehouse.project.controllers;

import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.OwnedGame;
import com.gamehouse.project.models.User;
import com.gamehouse.project.models.WishlistGame;
import com.gamehouse.project.models.data.*;
import com.gamehouse.project.models.dto.GameUsernameDTO;
import com.gamehouse.project.services.APICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("wishlist")
public class WishlistGameController {

    @Autowired
    private WishlistGameRepository wishlistGameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameCategoryRepository gameCategoryRepository;

    @Autowired
    private GamePlatformRepository gamePlatformRepository;


    // Add game to Wishlist using long igdbCode & String username
    @PostMapping("/addGame")
    public ResponseEntity<String> addGameToWishlist (@RequestBody GameUsernameDTO gameUsernameDTO) throws Exception {

        // Checks if Game already added by user
        List<WishlistGame> wishlistByUser = wishlistGameRepository.findAllByUsername(gameUsernameDTO.getUsername());

        for (WishlistGame game : wishlistByUser) {
            if (game.getIgdbCode() == gameUsernameDTO.getIgdbCode()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Game already added to Wishlist.");
            }
        }


        WishlistGame gameWishlist = new WishlistGame();

        // Search gameRepository for game -- if NOT will save game to gameRepository
        Optional<Game> getGame = gameRepository.findByIgdbCode(gameUsernameDTO.getIgdbCode());

        if (getGame.isPresent()) {
            gameWishlist.setGame(getGame.get());
            gameWishlist.setGameName(getGame.get().getName());
            gameWishlist.setIgdbCode(getGame.get().getIgdbCode());

        } else {

            // Uses igdbCode to retrieve game from APICallService & save to gameRepository
            APICallService newApiCall = new APICallService();
            Game addNewGame = newApiCall.getGamebyIDGBCODE(gameUsernameDTO.getIgdbCode());

            gameCategoryRepository.saveAll(addNewGame.getGameCategories());
            gamePlatformRepository.saveAll(addNewGame.getGamePlatforms());

            gameRepository.save(addNewGame);


            // THEN, Search gameRepository to find game based on igdbCode to set game as Wishlist item
            Game wishlistGame = gameRepository.findByIgdbCode(gameUsernameDTO.getIgdbCode()).get();
            gameWishlist.setGame(wishlistGame);
            gameWishlist.setGameName(wishlistGame.getName());
            gameWishlist.setIgdbCode(wishlistGame.getIgdbCode());
        }

        // Search UserRepository to find User based on username
        User getUser = userRepository.findByUsername(gameUsernameDTO.getUsername());

        gameWishlist.setUser(getUser);
        gameWishlist.setUsername(getUser.getUsername());

        // Saves wishlisted game to repo
        wishlistGameRepository.save(gameWishlist);

//        wishlistGameRepository.saveAll(gameWishlist);
        return ResponseEntity.status(HttpStatus.CREATED).body("Game added to Wishlist");
    }



    // Get Wishlist Games by Username
    @GetMapping("/{username}")
    public List<WishlistGame> getWishlistGameByUsername (@PathVariable String username) {

        List<WishlistGame> wishlistByUser = new ArrayList<>();

        List<WishlistGame> wishlistByUsername = wishlistGameRepository.findAllByUsername(username);

        for (int i = 0; i < wishlistByUsername.size(); i++) {
            WishlistGame wishlistGame = new WishlistGame();

//            wishlistGame.setGame(wishlistByUsername.get(i).getGame());
//            wishlistGame.setUser(wishlistByUsername.get(i).getUser());
            wishlistGame.setUsername(wishlistByUsername.get(i).getUsername());
            wishlistGame.setGameName(wishlistByUsername.get(i).getGameName());
            wishlistGame.setIgdbCode(wishlistByUsername.get(i).getIgdbCode());

            wishlistByUser.add(wishlistGame);
        }

        return wishlistByUser;
    }


    // Removes Game from wishlistGameRepository
    @DeleteMapping("delete")
    public ResponseEntity<String> removeGame(@RequestBody GameUsernameDTO gameUsernameDTO) {

        // Pulls up Games Wishlist by Username
        List<WishlistGame> wishlistByUser = wishlistGameRepository.findAllByUsername(gameUsernameDTO.getUsername());

        for (WishlistGame game : wishlistByUser) {
            if (game.getIgdbCode() == gameUsernameDTO.getIgdbCode()) {
                wishlistGameRepository.deleteById(game.getId());
            }
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Game is removed from Wishlist.");
    }

}
