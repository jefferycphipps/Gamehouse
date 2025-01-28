package com.gamehouse.project.models.data;

import com.gamehouse.project.models.WishlistGame;
import org.springframework.data.repository.CrudRepository;

public interface WishlistGameRepository extends CrudRepository<WishlistGame, Integer> {
}
