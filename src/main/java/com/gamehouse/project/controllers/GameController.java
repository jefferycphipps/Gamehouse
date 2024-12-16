package com.gamehouse.project.controllers;


import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.data.GameRepository;
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

    //Create new game
    @PostMapping("/saveGame")
    public ResponseEntity<Game> newGame(@RequestBody Game game) throws URISyntaxException{
        Game newGame = gameRepository.save(game);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGame);

    }


    @GetMapping("/games")
    public List<Game> getEvents() {
        return (List<Game>) gameRepository.findAll();
    }

    @GetMapping("/{id}")
    public Game getEvent(@PathVariable int id) {
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
