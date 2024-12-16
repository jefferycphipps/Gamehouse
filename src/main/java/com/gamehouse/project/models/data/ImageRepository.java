package com.gamehouse.project.models.data;

import com.gamehouse.project.models.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

    Optional<Image> findByName(String fileName);
}