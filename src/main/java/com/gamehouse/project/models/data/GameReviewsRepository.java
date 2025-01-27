package com.gamehouse.project.models.data;

import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.GameReviews;
import com.gamehouse.project.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameReviewsRepository extends CrudRepository <GameReviews, Integer> {
    Optional<GameReviews> findByIgdbCode (long igdbCode);
    Optional<GameReviews> findByUsername (String username);
    Optional<GameReviews> findByGameName (String gameName);
}
