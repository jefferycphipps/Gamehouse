package com.gamehouse.project.models.data;

import org.springframework.data.repository.CrudRepository;
import com.gamehouse.project.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository <User,Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByName(String name);
}
