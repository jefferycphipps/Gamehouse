package com.gamehouse.project.controllers;

import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.WishlistGame;
import com.gamehouse.project.models.data.UserRepository;
import com.gamehouse.project.models.data.WishlistGameRepository;
import com.gamehouse.project.models.dto.WishlistGameDTO;
import com.gamehouse.project.services.APICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wishlist")
public class WishlistGameController {

    @Autowired
    private WishlistGameRepository wishlistGameRepository;

    @Autowired
    private UserRepository userRepository;


    // Add game to Wishlist using long igdbCode & String username
//    @PostMapping("/addGame")
//    public ResponseEntity<String> addGameToWishlist (@RequestBody WishlistGameDTO wishlistGameDTO) throws Exception {
//
//        WishlistGame gameWishlist = new WishlistGame();
//
//        // Search gameRepository to see if game save, if NOT will save game to gameRepository
//
//
//        // Uses igdbCode to retrieve game from APICallService
//        APICallService newApiCall = new APICallService();
//        Game getGame = newApiCall.getGamebyIDGBCODE(wishlistGameDTO.getIgdbCode());
//
//        gameWishlist.setIgdbCode(getGame.getIgdbCode());
//        gameWishlist.
//
//    }


}
