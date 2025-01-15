package com.gamehouse.project.models.data;

import com.gamehouse.project.models.Game;
import com.gamehouse.project.models.GameReviews;
import com.gamehouse.project.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameReviewsRepository extends CrudRepository <GameReviews, Integer> {
    Optional<GameReviews> findByIgdbCode (int igdbCode);
    Optional<GameReviews> findByGame (Game game);
    Optional<GameReviews> findByUsername (User username);
//    Optional<User> findByUsername (String username);
}
