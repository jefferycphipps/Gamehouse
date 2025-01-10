package com.gamehouse.project.models.data;

import com.gamehouse.project.models.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImageRepository extends CrudRepository<Image, Integer> {

    Image findByName(String fileName);
}