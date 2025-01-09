package com.gamehouse.project.models.data;


import com.gamehouse.project.models.GameCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameCategoryRepository extends CrudRepository<GameCategory, Integer> {
    Optional<GameCategory> findByigdbCode(int igdbCode);
}
