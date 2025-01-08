package com.gamehouse.project.models.data;

import com.gamehouse.project.models.GamePlatform;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlatformRepository extends CrudRepository <GamePlatform, Integer> {
}
