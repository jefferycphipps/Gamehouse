package com.gamehouse.project.Models.data;
import org.springframework.data.repository.CrudRepository;
import com.gamehouse.project.Models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository <User,Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    User findByName(String name);
}
