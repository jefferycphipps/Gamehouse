package com.gamehouse.project.controllers;


import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.GameJson;
import com.gamehouse.project.services.APICallService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("search")
public class SearchController {

    @PostMapping("/getGames")
    public List<Game> gameSearch(@RequestParam("searchItem") String searchItem) throws Exception {
        APICallService caller = new APICallService();

        return caller.getGames(searchItem);
    }


}
