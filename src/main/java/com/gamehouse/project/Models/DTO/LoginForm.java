package com.gamehouse.project.models.dto;

import com.gamehouse.project.models.AbstractEntity;
import com.gamehouse.project.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginForm  {
    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Password is required.")
    @Size(min = 3, max = 12, message = "Keep between 3 to 12 characters.")
    private String password;

public LoginForm(String username, String password){
    this.username=username;
    this.password=password;
}
public LoginForm(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
