package com.gamehouse.project.data;

import com.gamehouse.project.models.GameReviews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameReviewsRepository extends CrudRepository <GameReviews, Integer> {
}
