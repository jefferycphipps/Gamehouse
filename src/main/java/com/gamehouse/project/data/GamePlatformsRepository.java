package com.gamehouse.project.data;

import com.gamehouse.project.models.GamePlatforms;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlatformsRepository extends CrudRepository <GamePlatforms, Integer> {
}
