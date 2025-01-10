package com.gamehouse.project.Models;

import jakarta.persistence.Entity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Entity
public class User extends AbstractEntity{
  private static final   BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
    private String username;
    private String email;
    private String pwHash;

    public User(String username, String email, String pwHash) {
        super();

        this.username = username;
        this.email = email;
        this.pwHash = pwHash;

    }
    public User(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash(String pwHash) {
        this.pwHash = pwHash;
    }

    public boolean isMatchingPassword(String password) {
        return passwordEncode.matches(password,pwHash);
    }
}
