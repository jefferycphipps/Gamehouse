package com.gamehouse.project.data;

import com.gamehouse.project.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository <Game, Integer> {
}
