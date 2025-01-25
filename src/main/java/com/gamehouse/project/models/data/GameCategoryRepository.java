package com.gamehouse.project.models.data;


import com.gamehouse.project.models.GameCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameCategoryRepository extends CrudRepository<GameCategory, Integer> {
    Optional<GameCategory> findByigdbCode(long igdbCode);
    Optional<GameCategory> findByName (String name);
}
