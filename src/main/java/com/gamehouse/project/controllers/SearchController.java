package com.gamehouse.project.controllers;


import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.GameJson;
import com.gamehouse.project.models.GameLight;
import com.gamehouse.project.models.data.GameCategoryRepository;
import com.gamehouse.project.models.data.GamePlatformRepository;
import com.gamehouse.project.services.APICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private GameCategoryRepository gameCategoryRepository;

    @Autowired
    private GamePlatformRepository gamePlatformRepository;

    @PostMapping("/getGames")
    public List<GameLight> gameSearch(@RequestParam("searchItem") String searchItem) throws Exception {
        APICallService caller = new APICallService();
        if(gameCategoryRepository.count()==0) {
            gameCategoryRepository.saveAll(caller.saveGameGenres());
        }
        if(gamePlatformRepository.count()==0){
            gamePlatformRepository.saveAll(caller.saveGamePlatforms());
        }


        return caller.getGamesLight(searchItem);
    }

    @PostMapping("/getGamesbyCode")
    public Game gameSearchbyIDGBCode(@RequestParam("IDGBCode") long IDGBCode) throws Exception {
        APICallService caller = new APICallService();

        return caller.getGamebyIDGBCODE(IDGBCode);
    }

}
