package com.gamehouse.project.Models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User extends AbstractEntity{
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String username;
    @NotNull
    private String pwHash;
    @NotNull
    private String email;

    public User(){}

    public User(String password, String email) {
        super();

        this.pwHash = encoder.encode(password);
        this.email = email;
    }

    public boolean isMatchingPassword(String password){
        return encoder.matches(password, pwHash);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
