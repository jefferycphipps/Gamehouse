package com.gamehouse.project.controllers;


import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.GameJson;
import com.gamehouse.project.models.GameLight;
import com.gamehouse.project.models.data.GameCategoryRepository;
import com.gamehouse.project.models.data.GamePlatformRepository;
import com.gamehouse.project.services.APICallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("search")
@CrossOrigin("http://localhost:5173/")
public class SearchController {

    @Autowired
    private GameCategoryRepository gameCategoryRepository;

    @Autowired
    private GamePlatformRepository gamePlatformRepository;
//@RequestBody String starter
    @PostMapping("/startup")
    public ResponseEntity<String> startUp() throws Exception {
        //System.out.println(starter);
//        if(starter.equals("go")) {
            APICallService caller = new APICallService();
            if (gameCategoryRepository.count() == 0) {
                gameCategoryRepository.saveAll(caller.saveGameGenres());
            }
            if (gamePlatformRepository.count() == 0) {
                gamePlatformRepository.saveAll(caller.saveGamePlatforms());
            }
            return ResponseEntity.ok("startup ran successfully");
//        }else
//            return ResponseEntity.ok("wrong key");
    }

    @PostMapping("/getGames")
    public List<GameLight> gameSearch(@RequestBody String searchItem) throws Exception {
        APICallService caller = new APICallService();

        return caller.getGamesLight(searchItem);
    }

    @PostMapping("/getGamebyID")
    public Game gameSearchbyIDGBCode(@RequestBody long IDGBCode) throws Exception {
        APICallService caller = new APICallService();

        return caller.getGamebyIDGBCODE(IDGBCode);
    }

    @PostMapping("/getGamebyIDs")
    public List<Game> gameSearchbyIDGBCodes(@RequestBody long[] IDGBCodes) throws Exception {
        APICallService caller = new APICallService();
        List<Game> gameList = new ArrayList<>();
        for (int x = 0; x < IDGBCodes.length; x++) {
            gameList.add(caller.getGamebyIDGBCODE(IDGBCodes[x]));
        }
        return gameList;
    }


}
