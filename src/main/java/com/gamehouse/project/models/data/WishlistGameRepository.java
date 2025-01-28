package com.gamehouse.project.models.data;

import com.gamehouse.project.models.WishlistGame;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WishlistGameRepository extends CrudRepository<WishlistGame, Integer> {
    List<WishlistGame> findAllByUsername (String username);
}
