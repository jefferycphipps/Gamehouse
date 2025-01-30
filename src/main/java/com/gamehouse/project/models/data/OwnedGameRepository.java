package com.gamehouse.project.models.data;

import com.gamehouse.project.models.OwnedGame;
import com.gamehouse.project.models.WishlistGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnedGameRepository extends CrudRepository<OwnedGame, Integer> {
    List<OwnedGame> findAllByUsername (String username);
    WishlistGame findByIgdbCode (long igdbCode);
    WishlistGame findByUsername (String username);
}
