package com.gamehouse.project.data;

import com.gamehouse.project.models.GameCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCategoryRepository extends CrudRepository <GameCategory, Integer> {
}
