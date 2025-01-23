package com.gamehouse.project.models.data;

import com.gamehouse.project.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends CrudRepository <Game, Integer> {
//    Optional<Game> findByIgdbCode (long IGDBCode);
    Optional<Game> findByIgdbCode (long igdbCode);

}
