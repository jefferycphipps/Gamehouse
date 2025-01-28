package com.gamehouse.project.controllers;

import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.User;
import com.gamehouse.project.models.WishlistGame;
import com.gamehouse.project.models.data.GameRepository;
import com.gamehouse.project.models.data.UserRepository;
import com.gamehouse.project.models.data.WishlistGameRepository;
import com.gamehouse.project.models.dto.WishlistGameDTO;
import com.gamehouse.project.services.APICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    // Add game to Wishlist using long igdbCode & String username
    @PostMapping("/addGame")
    public ResponseEntity<String> addGameToWishlist (@RequestBody WishlistGameDTO wishlistGameDTO) throws Exception {

        WishlistGame gameWishlist = new WishlistGame();

        // Search gameRepository for game -- if NOT will save game to gameRepository
        Optional<Game> getGame = gameRepository.findByIgdbCode(wishlistGameDTO.getIgdbCode());

        if (getGame.isPresent()) {
            gameWishlist.setGame(getGame.get());
            gameWishlist.setGameName(getGame.get().getName());
            gameWishlist.setIgdbCode(getGame.get().getIgdbCode());

        } else {

            // Uses igdbCode to retrieve game from APICallService & save to gameRepository
            APICallService newApiCall = new APICallService();
            Game addNewGame = newApiCall.getGamebyIDGBCODE(wishlistGameDTO.getIgdbCode());
            gameRepository.save(addNewGame);

            // THEN, Search gameRepository to find game based on igdbCode to set game as Wishlist item
            Game wishlistGame = gameRepository.findByIgdbCode(wishlistGameDTO.getIgdbCode()).get();
            gameWishlist.setGame(wishlistGame);
            gameWishlist.setGameName(wishlistGame.getName());
            gameWishlist.setIgdbCode(wishlistGame.getIgdbCode());
        }

        // Search UserRepository to find User based on username
        User getUser = userRepository.findByUsername(wishlistGameDTO.getUsername());

        gameWishlist.setUser(getUser);
        gameWishlist.setUsername(getUser.getUsername());

        // Saves wishlisted game to repo
        wishlistGameRepository.save(gameWishlist);

        return ResponseEntity.status(HttpStatus.CREATED).body("Game added to Wishlist");
    }


}
